package com.ccf.selenium.caotest;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumWebDriverTest {
    private static String driver = "";
    private static String fireFox = "";

    static {
        //读取配置文件中关于Selenium的配置
        Properties properties = new Properties();
        InputStream in = Object.class.getResourceAsStream("/selenium.properties");
        try {
            properties.load(in);
            //驱动路径
            driver = properties.getProperty("webdriver.gecko.driver");
            //浏览器路径
            fireFox = properties.getProperty("webdriver.firefox.bin");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static WebDriver webDriverPC = new FirefoxDriver();


    public static void main(String[] args) {
        String content = webDriverPC.get("http://www.baidu.com");

    }
}
