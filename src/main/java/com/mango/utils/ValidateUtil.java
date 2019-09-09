package com.mango.utils;

import java.util.Map;

public class ValidateUtil {
    public static boolean validate(Configuration configuration) {
        if (configuration.getDatabaseUrl().equals(""))
            return false;
        if (configuration.getDbUser().equals(""))
            return false;
        if (configuration.getDriverClass().equals(""))
            return false;
        if (configuration.getDbPassword().equals(""))
            return false;
        if (configuration.getEsUrl().equals(""))
            return false;
        return true;
    }
}
