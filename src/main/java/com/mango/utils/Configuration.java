package com.mango.utils;

import java.util.Map;

public class Configuration {
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getEsUrl() {
        return esUrl;
    }

    public void setEsUrl(String esUrl) {
        this.esUrl = esUrl;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    private String sql;
    private String databaseUrl;
    private String dbUser;
    private String dbPassword;
    private String driverClass;
    private String esUrl;
    private String index;
    private String documentId;
    private String schedule;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Configuration(Map config){
        Map<String,String> inputMap=(Map) config.get("input");
        Map<String,String> outputMap=(Map) config.get("output");
        Map<String,String> filterMap=(Map) config.get("filter");
        Map<String,String> scheduleMap=(Map) config.get("schedule");
        this.setDatabaseUrl(inputMap.get("url"));
        this.setDbUser(inputMap.get("user"));
        this.setDbPassword(inputMap.get("password"));
        this.setDriverClass(inputMap.get("driverClass"));
        this.setSql(inputMap.get("sql"));
    }

    public String getSql() {
        return sql;
    }
}
