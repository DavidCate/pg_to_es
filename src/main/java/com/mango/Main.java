package com.mango;

import com.mango.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;


public class Main {
    private final static Logger logger= LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main main=new Main();
        try {
            main.main("config.yaml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void main(String filePath) throws Exception {
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(filePath);
        Map config=YamlUtil.getConfig(inputStream);
        Configuration conf=new Configuration(config);
        if (ValidateUtil.validate(conf)){
            Connection connection=PgConnector.getConnection(conf);
            Schedule schedule=new Schedule(conf);
            PageSQL pageSQL=new PageSQL(conf.getSql(), conf);
            while (true){

                while (pageSQL.next()){
                    PreparedStatement preparedStatement=connection.prepareStatement(pageSQL.nextSQL());
                    ResultSet resultSet=preparedStatement.executeQuery();
                    Filter filter=new Filter(resultSet,conf);
                    List<String> values= filter.filter();
                    DBUtil dbUtil=new DBUtil(connection);
                    List<String> fields=dbUtil.getFields();
                    Document document=new Document(fields,values);
                    List<String> messages=document.getMessages();
                    ESUtil esUtil=new ESUtil(conf);
                    if(esUtil.dumps(messages)){

                    }else {
                        throw new Exception("存储失败");
                    }
                }
                Thread.sleep(schedule.getSleepTime());
                pageSQL.update();
            }
        }else {
            throw new Exception("配置文件错误");
        }
    }
    /*
        1.解析配置文件
        2.校验配置文件
            死循环执行
            3.data input
            4.data filter
            5.data output
     */
}
