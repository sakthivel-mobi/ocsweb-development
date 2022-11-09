package com.mobi.ocs.utilities;

/**
 * AppFunctions
 */
public class AppFunctions {

    public final static String getUserRole(String authority) {
        return authority.replace("[", "").replace("]", "").replace("ROLE_", "").toLowerCase();
    }
}