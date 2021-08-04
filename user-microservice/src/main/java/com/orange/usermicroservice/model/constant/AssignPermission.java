package com.orange.usermicroservice.model.constant;

import com.orange.usermicroservice.model.enums.AdminRole;

import java.util.HashSet;
import java.util.Set;

public class AssignPermission {
    public static Set<String> assignPermissions(AdminRole adminRole){
        Set<String> assignedPermissions = new HashSet<>();
        switch (adminRole){
            case OPE_ADMINISTRATOR:
                assignedPermissions.add(UserPermission.PERMISSION_ALL);
                break;
            case VIEWER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                break;
            case API_FACTORY_MEMBER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ_ALL));
                break;
            case SUBSCRIPTION_MANAGER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.NOTIFY));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.DELETE));
                break;
            case API_ADMINISTRATOR:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.NOTIFY));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.DELETE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.PUBLISH));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.CONVERT));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.DELETE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.DELETE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.CONTENTS, UserPermission.Right.PUBLISH));
                break;
            case SELF_PUBLISHER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS+".self", UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                break;
            case OIDC_ADMINISTRATOR:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATION_SCOPES, UserPermission.Right.UPDATE));
                break;
            case USER_MANAGER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.CREATE));
                break;
            case EXPOSURE_MANAGER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ_ALL));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.EXPOSE));
                break;
            case API_CONTENT_MANAGER:
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PORTALS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.DEVELOPERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.ADMINISTRATORS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.APPLICATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.SUBSCRIPTIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.OFFERS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.PRODUCTS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.TERMS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.INVITATIONS, UserPermission.Right.READ));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.CONTENTS, UserPermission.Right.UPDATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.CONTENTS, UserPermission.Right.CREATE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.CONTENTS, UserPermission.Right.DELETE));
                assignedPermissions.add(UserPermission.permission(UserPermission.Resource.CONTENTS, UserPermission.Right.PUBLISH));
                break;
            default:
                break;
        }
        return assignedPermissions;
    }
}
