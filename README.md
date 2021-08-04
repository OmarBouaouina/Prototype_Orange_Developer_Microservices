---
description: "Learn about the Microservices' prototype for Orange Developer"
---
# Orange Developer Microservices Prototype (still in development)

This microservices project serves as a prototype, to demonstrate how to split the Orange Developer service following a microservices architecture.

To achieve said goal, we use *Java* with *Spring Framework* (*Spring Boot*, *Spring Cloud*, etc.), *Consul*, *RabbitMQ*, *MySQL* and *Docker/Docker Compose*.

Note that this prototype does not cover every aspect of the Orange Developer service, and can eventually be improved upon.

# Table of Content
* [Prerequisites](#prerequesites)
* [Prototype Architecture](#architecture)
    * [Consul](#consul)
        * [Service Registry](#registry)
        * [Key/Value Store](#kvStore)
    * [RabbitMQ](#rabbitmq)
        * [Jackson-JSON Serializer/Deserializer](#jackson)
        * [RabbitMQ Producer/Publisher](#producer)
        * [RabbitMQ Consumer/Subscriber](#consumer)
    * [API Gateway](#gateway)
* [Testing Locally](#localhost)
    * [Consul](#consulLocal)
    * [RabbitMQ](#rabbitMQLocal)
    * [MySQL](#mysqlLocal)
    * [Spring Boot Services](#servicesLocal)
* [Testing in a Dockerized Environment](#dockerized)

## <a name="prerequesites"></a>Prerequisites
- IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) was used during this internship, but you can use any other IDE that supports Java with Spring Framework
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [RabbitMQ](https://www.rabbitmq.com/download.html) ([Erlang](https://www.erlang.org/downloads) required if using Windows)
- [Consul](https://www.consul.io/downloads)
- [MySQL](https://www.mysql.com/downloads/)
- [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/install/)

## <a name="architecture"></a>Prototype Architecture
The following prototype consists of 7 different services:
- [User Microservice](/user-microservice): User (Admin/Developer)'s management
- [Admin Microservice](/admin-microservice): Orange Developer' services management, admin side (editable)
- Developer Microservice: Orange Developer' services management, developer side (published)
- Content Microservice: ...
- [API Gateway](/gateway): Proxies the different microservices
- Consul: Service Registry/Service Discovery
- RabbitMQ: Message broker, for asynchronous inter-microservices communications

![Prototype Architecture](/images/Architecture.png)

### <a name="consul"></a>Consul
**Consul** was chosen as this architecture' *Service Registry/Service Discovery*, as it comes with a centralized *Key/Value Store* that could be utilized for different services' proper and/or global configuration.

Consul's **default port is 8500** and it can be used through a CLI, or through its own UI:

<kbd>![Consul's registered services](/images/Consul_1.png)</kbd>

The Consul UI's homepage gives us a glimpse of the currently running instances of services that are registered in Consul. Selecting a service to inspect gives us a look of each instance' status (mainly its health check through **Spring Boot Actuator**):

<kbd>![Registered service's status (using Spring Boot Actuator)](/images/Consul_2.png)</kbd>

#### <a name="registry"></a>Service Registry
For a service to be registered in Consul, it should have its main class annotated with `@EnableDiscoveryClient`, as well as have (at least) the following properties:

```
#Consul's address
spring.cloud.consul.host

#Consul's port
spring.cloud.consul.port

#URL to check for the instance's health status
spring.cloud.consul.discovery.healthCheckUrl
```

The use of Consul not only allows us to have a register of every running "healthy" instance (Service Registry), but it is also what the API Gateway consults to determine the path to route the client's request to (Service Discovery).

Both the API Gateway and services are registered in Consul, but only the latter are required to be *Discoverable*.

#### <a name="kvStore"></a>Key/Value Store
When it comes to utilizing Consul's Key/Value Store mechanism, there are multiple things to consider:

- If the Key/Value is meant to be used by one service only, the Key/Value should be created in the `config/applicationName` directory within Consul (with **applicationName** being the same as the one set in `spring.application.name`).
- If the Key/Value is meant to be shared between multiple services, the Key/Value should be created in the `config/application` directory within Consul.
- If the Key/Value is to be created through the Consul UI, one must specifify `directory/keyName` in the **Key or folder** field.

    In the example below, the key **testing** was created in the `config/application` directory to be shared between multiple services:

<kbd>![Creating Key/Values to be consumed by different services](/images/Consul_3.png)</kbd>

- If the Key/Value is to be imported from a file, it has to be done through a CLI and there are certains rules to follow:

    1) The Key/Value must be stored in a **JSON** file.
    2) The **JSON** file must be structured like so:

    ```json
    [{
        "key": "config/application/testing",
        "value": "aGVsbG86IEhlbGxvV29ybGQ="
    }]
    ```

    The **"key"** indicates the `directory/keyName`, and the **"value"** indicates the value of said key, encoded in **Base64** (a Consul requirement).
    
    In the example above, "key" was set to `config/application/testing` while "value" was set `hello: HelloWorld`, just like in the UI example (only this time the value was encoded in Base64).

    3) After starting Consul: to import the **JSON** file, run the following command within the directory where Consul was extracted after download:

    ```
    #If on Linux
    ./consul kv import @fileName.json

    #If on Windows
    consul.exe kv import @fileName.json
    ```

To import a Key/Value into a service, the properties `spring.cloud.consul.config.format` and `spring.cloud.consul.config.data-key` must be present in the application's **bootstrap.yml** file. The former should be set to **YAML**, as it is the chosen format by default (though it can be changed in Consul to **JSON** or **HCL**), while the latter with the key's name which we want to import its value.

For example, if the key created in Consul is **testing** (that of our previous example), then the application's **bootstrap.yml** file should contain the following properties to use the values of said key:

```yaml
spring:
    cloud:
        consul:
            config:
                format: YAML
                data-key: testing
```

It should be noted that `spring.cloud.consul.config.data-key` allows for a single key only, as we cannot import multiple Keys into a single service (something to take into consideration when creating Keys/Values).

To utilize the imported values, each service has a **ConsulConfig.java** file, in which we use the `@Value` annotation to inject these values:

```java
/*
Package and Imports
*/
@Configuration
@RefreshScope
public class ConsulConfig {
    
    @Value("${hello}")
    private String hello; //hello attribute has the "HelloWorld" String as value

    /*
    Rest of the configuration
    */
}
```

As for the `@RefreshScope` annotation, it allows for attributes bounded with `@Value` to be refreshed just after property sources' reloading, by calling the `/actuator/refresh` endpoint.

### <a name="rabbitmq"></a>RabbitMQ
**RabbitMQ** was chosen as this architecture's message broker. Its **default port is 5672** and it can be used through a CLI, or through its own management plugin's UI (**default port is 15672**):

<kbd>![RabbitMQ's Management Homepage](/images/RabbitMQ.png)</kbd>

With RabbitMQ, there are three elements to understand and to take into account:
- **Queue**: sequential data structure, in which messages can be enqueued (added) at the tail by a producer/publisher and dequeued (consumed) from the head by a consumer/subscriber.
- **Exchange**: message routing agent, defined by the virtual host within RabbitMQ. It is responsible for routing messages to one or multiple queues.
- **Binding**: does as its name implies: it binds a Queue to an Exchange.

<kbd>![RabbitMQ's Standard Message Flow](/images/RabbitMQ_1.png)</kbd>

There's usually a fourth element, called the **Routing key**, which is an attribute the Exchange looks at when deciding how to route a message to Queue(s). However, whether to use it or not depends on the type of Exchange used. In the case of the **Fanout Exchange** type used with this prototype, the routing key isn't necessary. So, it is just set as an empty String in the **RabbitTemplate.convertAndSend()** method.

To explain inter-microservices communications with RabbitMQ, we'll be using the "Create an Admin" use case as an example, which has the [User Microservice](/user-microservice) publish an event/message to be consumed by the [Admin Microservice](/admin-microservice):

#### <a name="jackson"></a>Jackson-JSON Serializer/Deserializer
The first thing to configure is the Java classes-JSON **serialization/deserialization**:

```java
/*
Package and Imports
*/
@Configuration
public class RabbitTemplateConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
```

With this configuration, we override the default `RabbitTemplate` bean created by Spring Boot with another one that will use a message converter of type `Jackson2JsonMessageConverter` (this only affects to messages sent via `RabbitTemplate`). The bean `Jackson2JsonMessageConverter` will take care of deserializing the **JSON messages** to **Java classes**, using a default Jackson’s **ObjectMapper**.

#### <a name="producer"></a>RabbitMQ Producer/Publisher
For each service producing/publishing an event/message, a `rabbitMQConfig` directory was created, housing a configuration file for each entity.

Each of these configuration files has, for each **Queue** instance created, an **Exchange** instance as well as a **Binding** instance that binds the Queue to the Exchange (both Queue and Exchange constructors require a string that represents its name):

```java
/*
Package and Imports
*/
public class AdminConfig {
    
    @Bean
    public Queue adminCreationAdminMSQueue(){ return new Queue("admin_creation_adminMS"); } //Queue creation

    @Bean
    public FanoutExchange adminCreationExchange(){ return new FanoutExchange("admin_creation_exchange"); } //Exchange creation

    @Bean
    public Binding adminCreationAdminMSBinding(){ return BindingBuilder.bind(adminCreationAdminMSQueue).to(adminCreationExchange); }
    //Binding creation (using the BindingBuilder to bind the Queue to the FanoutExchange created previously)

    /*
    Rest of the configuration
    */
}
```

Producing an event/message here occurs within the `controllers`:

```java
/*
Package and Imports
*/
@RestController
@RequestMapping("users/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<AdminPostOperationDTO> createAdmin(@RequestBody AdminCreationDTO admin) {
        AdminPostOperationDTO createdAdmin = adminService.createAdmin(admin);
        if(createdAdmin != null) {
            //convertAndSend(String exchangeName, String routingKey, Object objectToConvertAndSend)
            rabbitTemplate.convertAndSend(AdminConfig.ADMIN_CREATION_EXCHANGE, "", new AdminProducedDTO(createdAdmin)); 
            /*
            Rest of the function
            */
    }
    /*
    Rest of the controller
    */
}
```

With the Admin creation request, once it is successfully created within the [User Microservice](/user-microservice), the `RabbitTemplate.convertAndSend()` is called. We pass to it the appropriate Exchange, Routing Key (which is an empty String in the case of Fanout Exchanges), and the object to send. Here, we opted for creating an `AdminProducedDTO` DTO, which contains some informations about the created Admin to send.

#### <a name="consumer"></a>RabbitMQ Consumer/Subscriber
For each service consuming an event/message, a `rabbitMQListener` directory was created, housing a configuration file for each entity.

Each of these configuration files has a number of listener methods: each one correspond to a certain operation, and each one is annotated with `@RabbitListener(queues = queueName)` where **queueName** is a String representing the name of the queue from which to consume:

```java
/*
Package and Imports
*/
public class AdminListener {

    @Autowired
    private AdminRepository adminRepository;
    
    @RabbitListener(queues = "admin_creation_adminMS")
    public void adminCreationListener(AdminConsumedDTO consumedAdmin){ //consumedAdmin being the event/message consumed from the Queue
        AdminEntity admin = new AdminEntity(consumedAdmin.getId(), consumedAdmin.getNaturalId(), consumedAdmin.getEmail(),
        consumedAdmin.getFirstName(), consumedAdmin.getLastName(), consumedAdmin.getUserType(), consumedAdmin.getRoles(),
        consumedAdmin.getPermissions());
        adminRepository.save(admin);
        
        /*
        Rest of the function
        */
    }
    /*
    Rest of the listener
    */
```

It should be noted that the `AdminConsumedDTO` DTO is the same as the `AdminProducedDTO`. Once consumed, the Java object can be used for different operations (in this case, it is just used to create and save a lighter version of the Admin within the [Admin Microservice](/admin-microservice)).

### <a name="gateway"></a>API Gateway
When it comes to the API Gateway, we've opted for **Spring Cloud Gateway** over **ZUUL** with the Spring Framework, as it is now the preferred implementation from the Spring Cloud Team.

Configuration wise, there aren't any particular Java files write for the Gateway service, just some properties to consider and configure.

These properties are numerous, all of which are a part of **spring.cloud.gateway**. The ones that were configured to have a working API Gateway were `spring.cloud.gateway.discovery.locator.enabled`, `spring.cloud.gateway.metrics.enabled` and `spring.cloud.gateway.routes`:

```yaml
spring:
    cloud:
        gateway:
            metrics:
                enabled: true
            discovery:
                locator:
                    enabled: true
            routes:
                - id: UserMicroserviceApplication
                uri: lb://UserMicroserviceApplication
                predicates:
                    - Path=/users/**
#                filters:
#                    - RewritePath=/(?<segment>.*), /user-service/$\{segment}
```

To explain these properties a bit more:
- Setting **spring.cloud.gateway.discovery.locator.enabled** to `true` allows for DiscoveryClient gateway's integration.
- Setting **spring.cloud.gateway.metrics.enabled** to `true` allows for metrics' data collection.
- When it comes to **spring.cloud.gateway.routes**, things are a bit different. This is where we configure our list of Routes, so that the Gateway will know to which service to reroute an incoming request. There are sub-properties to configure for each Route (at least the ones mentioned below):

    1) An id property, given to it route by configuring the `- id` property.
    2) The destination URL, by configuring the `uri` property.
    3) A list of predicates, through the use of the `predicates` property. Predocated are recognized by the predicate type (Path, Header, Cookie, Query), followed by an equal sign `=`, followed by argument values seperated by commas `,`. In the provided screenshot example, we used the Path predicate with the value `/users/**`. With this, whenever we make a request to `/users/**`, it will direct to `lb://UserMicroserviceApplication` mentioned in the *uri* property (elaborate a bit on Consul's inclusion).

At some point, the `spring.cloud.gateway.routes.filters` were used, as one of these filters allowed to rewrite a segment of the given path to match with that of an API (the commented lines in the example above indicate that anything that comes after */users/* will be rewritten after */user-service/*).

## <a name="localhost"></a>Testing Locally
### <a name="consulLocal"></a>Consul
The first thing to do is to start a Consul agent, as services need to be registered for further operations.

To do so, in development mode: run the following command within the directory where Consul was extracted after download:

```
#If on Linux
./consul agent -dev 

#If on Windows
consul.exe agent -dev 
```

**WARNING: Never run Consul agent in `-dev` mode in production.**

Next, if a Key/Value store configuration is required (which is the case here), it should be done either manually, or through the import command inputted into another CLI (see [Key/Value Store](#kvStore)).

To stop a Consul agent, run the following command within Consul's directory:

```
#If on Linux
./consul leave 

#If on Windows
consul.exe leave
```

### <a name="rabbitMQLocal"></a>RabbitMQ
The second thing to have running is our message broker, RabbitMQ.

After downloading and installing [RabbitMQ](https://www.rabbitmq.com/download.html) (also [Erlang](https://www.erlang.org/downloads) if using Windows), things will differ depending on the OS in question.

For Windows, the RabbitMQ service should be running by default. If it isn't the case, run the following command within the `sbin` directory of RabbitMQ (the sbin directory contains a number of batch files):

```
rabbitmq-service.bat start
```

If the output from this command is `Service RABBITMQ_SERVICENAME started`, then the service was started successfully (confirm this by running Windows' `services.msc`).

With the service now running, RabbitMQ can be consulted through its default port (5672) using a CLI. However, to have a better visualization of what is going on within the broker, using its provided **Dashboard** would be the better option.

To do so, run the following command within RabbitMQ's aforementioned `sbin` directory:

```
rabbitmq-plugins.bat enable rabbitmq_managament
```

With this, we can now access RabbitMQ's Dashboard/Management UI through its default port (15672), with the default **Username/Password** being **guest/guest**.

(Infos on the case of Linux users).
### <a name="mysqlLocal"></a>MySQL
Third, a database for each Spring Boot service must be created (except for the Gateway, as it doesn't require one).

Throughout the internship, these databases were created manually, but using an SQL script could come in handy here.

Each database should be named as configured in each service's *application.yml* file (or changed to suit needs), and a MySQL user with username/password as **test/test** and enough privileges should be created (to change: bring necessary changes to each *application.yml*).
### <a name="servicesLocal"></a>Spring Boot Services
Finally, we have our services (including the API Gateway) to have them up and running.

When it comes to doing so, there are two solutions:

One is to run each service in a seperate instance of IDE, since each one of them is a Spring Boot application by itself.

The other is to create a simple **parent project**, with nothing but a `pom.xml` file. This project will act as a simple "container" for other projects. In it, we have a `<packaging>` tag and `<modules>` tags to consider:

```xml
<!--Beginning of the parent's pom.xml file-->
    <packaging>pom</packaging>

    <modules>
        <module>user-microservice</module>
        <module>admin-microservice</module>
        <module>gateway</module>
        <!--For each service, a module tag with its folder name is created-->
    </modules>
<!--Rest of the parent's pom.xml file-->
```

With this, we can simply import all our services' directories into the parent project's directory. And, as long as they are registered as modules within the parent's pom.xml file, they will be recognized, having at the end all the services running under a single IDE instance.
## <a name="dockerized"></a>Testing in a Dockerized Environment
...

