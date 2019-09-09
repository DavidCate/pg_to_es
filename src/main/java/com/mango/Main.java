package com.mango;

import com.mango.utils.PgConnector;
import com.mango.utils.ValidateUtil;
import com.mango.utils.YamlUtil;
import org.yaml.snakeyaml.Yaml;

import com.mango.utils.Configuration;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class Main {
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
        PgConnector pgConnector=null;
        if (ValidateUtil.validate(config)){
            Connection connection=PgConnector.getConnection(conf);
            connection.prepareStatement(config.get("input"))

        };
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
