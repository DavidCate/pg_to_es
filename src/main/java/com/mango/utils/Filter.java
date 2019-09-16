package com.mango.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class Filter {
    ResultSet origin;
    Configuration configuration;
    public Filter(ResultSet resultSet,Configuration configuration){
        this.origin=resultSet;
        this.configuration=configuration;
    }
    public List<String> filter() {
        List<String> res= null;
        try {
            res = this.doFilter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String> doFilter() throws SQLException {

        ResultSetMetaData resultSetMetaData=origin.getMetaData();
        int columnCount=resultSetMetaData.getColumnCount();
        for(int index=0;index<columnCount;index++){
            String fieldName=resultSetMetaData.getColumnName(index);
            int columnType=resultSetMetaData.getColumnType(index);
            String columnClassName=resultSetMetaData.getColumnClassName(index);

        }
        while (origin.next()){


        }
        return null;
    }
}
