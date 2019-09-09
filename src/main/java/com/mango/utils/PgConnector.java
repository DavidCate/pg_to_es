package com.mango.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Map;

public class PgConnector {
    public static Connection getConnection(Configuration configuration) throws Exception {
        Class.forName("");
        Connection connection=null;
        try {
             connection=DriverManager.getConnection("","","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(connection!=null){
            return connection;
        }else {
            throw new Exception("获取数据库连接失败");
        }
    }

    public ResultSet execute(String sql) throws Exception {
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("config.yaml");
        Map config=YamlUtil.getConfig(inputStream);
        Connection connection;
        PreparedStatement statement;
        try {
            String url=(String) config.get("url");
            String user=(String) config.get("user");
            String password =(String) config.get("password");
            String driver=(String) config.get("driverClass");
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
//            String sql="select * from fs_call_avail order by \"id\" limit 10 offset 0";
            statement=connection.prepareStatement(sql);
            ResultSet res=statement.executeQuery();
//            String column;
//            while (res.next()){
//                column=res.getString(1);
//                System.out.println(column);
//            }
            return res;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
