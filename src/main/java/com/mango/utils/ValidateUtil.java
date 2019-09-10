package com.mango.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ValidateUtil {
    private final static Logger logger= LoggerFactory.getLogger(ValidateUtil.class);
    public static boolean validate(Configuration configuration) {
        logger.info("校验配置...");
        if (configuration.getDatabaseUrl()==null||"".equals(configuration.getDatabaseUrl())){
            logger.error("databaseUrl为空");
            return false;
        }
        if (configuration.getDbUser()==null||configuration.getDbUser().equals("")){
            logger.error("dbUser为空");
            return false;
        }
        if (configuration.getDriverClass()==null||configuration.getDriverClass().equals("")){
            logger.error("driverClass为空");
            return false;
        }
        if (configuration.getDbPassword()==null||configuration.getDbPassword().equals("")){
            logger.error("dbPassword为空");
            return false;
        }
        if (configuration.getEsUrl()==null||configuration.getEsUrl().equals("")){
            logger.error("esUrl为空");
            return false;
        }
        return true;
    }
}
