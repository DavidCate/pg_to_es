package com.mango;

import com.mango.utils.PgConnector;
import com.mango.utils.YamlUtil;

import java.io.InputStream;
import java.sql.*;
import java.util.Map;

public class Test {
    public void test(){
        Connection connection;
        PreparedStatement statement;
        try {
            String url="jdbc:postgresql://flow4-wai.pg.rds.aliyuncs.com:3433/call_crm_flow";
            String user="postgres";
            String password = "Z8asuidn";
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,user,password);
            String sql="select * from fs_call_avail order by \"id\" limit 10 offset 0";
            statement=connection.prepareStatement(sql);
            ResultSet res=statement.executeQuery();
            String column;
            while (res.next()){
                column=res.getString(1);
                System.out.println(column);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRes(String sql) throws Exception {
        PgConnector pgConnector=new PgConnector();
        return pgConnector.execute(sql);
    }

    public static void main(String[] args) throws Exception {
        Test test=new Test();
//        test.test();
        String sql="select * from fs_call_avail limit 10 offset 0";
        ResultSet res=test.getRes(sql);
        while (res.next()){
            String xx=res.getString(1);
            System.out.println(xx);
        }
    }


//    public Map getConfig(String filepath) throws Exception {
//        InputStream inputStream=getClass().getClassLoader().getResourceAsStream(filepath);
//        Map config= YamlUtil.getConfig(inputStream);
//        return config;
//    }
//
//    public static void main(String[] args) throws Exception {
//        Main main=new Main();
//        Map config=main.getConfig("config.yaml");
//        System.out.println(config.get("xxx"));
//    }
}
