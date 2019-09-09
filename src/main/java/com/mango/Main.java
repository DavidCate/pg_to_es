package com.mango;

import com.mango.utils.YamlUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Main {
    public Map getConfig(String filepath) throws Exception {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream(filepath);
        Map config=YamlUtil.getConfig(inputStream);
        return config;
    }

    public static void main(String[] args) throws Exception {
        Main main=new Main();
        Map config=main.getConfig("config.yaml");
        System.out.println(config.get("xxx"));
    }
}
