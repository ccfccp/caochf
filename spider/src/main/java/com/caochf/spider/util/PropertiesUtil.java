package com.caochf.spider.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 读取properties文件.
     * @param propertyKey
     * @return
     */
    public static String getPropertyValue(String propertyKey){
        String propertyValue = null;
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        File propertiesFile = new File("F:/workspaces/myworkspaces/spider/src/main/resources/config/weather_conf.properties");
//        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("F:/workspaces/myworkspaces/spider/src/main/resources/config/weather_conf.properties");
        // 使用properties对象加载输入流
        try {
            InputStream in = new FileInputStream(propertiesFile);
            properties.load(in);
            //获取key对应的value值
            propertyValue = properties.getProperty(propertyKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}
