package com.mango.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class YamlUtil {
    public static Map getConfig(InputStream inputStream) throws Exception {
        Yaml yaml=new Yaml();
        Map map=null;
        try {
            map=yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map!=null){
            return map;
        }else {
            throw new Exception("加载yaml失败");
        }
    }
}
