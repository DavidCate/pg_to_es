package com.mango.utils;

import java.util.Map;

public class Configuration {
    private String sql;

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Configuration(Map config){
       setSql(config.get("input").get("sql"));

    }

    public String getSql(){

    }
}
