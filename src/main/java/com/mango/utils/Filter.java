package com.mango.utils;

import java.sql.ResultSet;
import java.util.List;

public class Filter {
    ResultSet origin;
    Configuration configuration;
    public Filter(ResultSet resultSet,Configuration configuration){
        this.origin=resultSet;
        this.configuration=configuration;
    }
    public List<String> filter() {
        return null;
    }
}
