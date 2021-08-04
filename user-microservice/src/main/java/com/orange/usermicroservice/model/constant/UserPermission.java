package com.orange.usermicroservice.model.constant;

public final class UserPermission {

    public static enum Right {
        CONVERT,
        CREATE,
        DELETE,
        EXPOSE,
        NOTIFY,
        READ,
        READ_ALL,
        PUBLISH,
        UPDATE;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public static enum Resource {
        ADMINISTRATORS,
        APPLICATIONS,
        APPLICATION_CLIENT_ID_STATUS,
        APPLICATION_SCOPES,
        CLIENTS,
        CONTENTS,
        DEVELOPERS,
        INVITATIONS,
        MACHINE_ADMINISTRATORS,
        OFFERS,
        PORTALS,
        PRODUCTS,
        SERVICES__KAPITEN("services/kapiten"),
        SUBSCRIPTIONS,
        TERMS;

        private String name;

        private Resource(String name) {
            this.name = name;
        }

        private Resource() {
        }

        public String toString() {
            if (name != null) {
                return name;
            }
            return name().toLowerCase();
        }
    }

    public static String permission(Resource resource, Right right) {
        return resource + ":" + right;
    }

    public static String permission(String resource, Right right) {
        return resource + ":" + right;
    }

    public static String specificPermission(Resource resource, Right right) {
        return resource + "@offers/%offerId%:" + right;
    }

    public static String offerPermGroupPermission(String groupName) {
        return "offers@%offerId%:" + groupName;
    }

    public static String getOfferPermission(String offerId) {
        return Resource.OFFERS + ":" + offerId;
    }

    public static String getOfferSpecPermission(String permission, String offerId) {
        return permission.replaceAll("%offerId%", offerId);
    }

    public static String getOfferPermGroupPermission(String permission, String offerId) {
        return permission.replaceAll("%offerId%", offerId);
    }

    public static final String PERMISSION_ALL = "*:all";
}