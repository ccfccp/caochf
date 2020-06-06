package com.caochf.spider.weather;

import com.caochf.spider.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HisWeatherSpider {
    public static void main(String[] args){
        String weatherUrl = PropertiesUtil.getPropertyValue("WEATHER_URL");
        String weatherCitys = PropertiesUtil.getPropertyValue("WEATHER_CITYS");
        String weatherTimeBegin = PropertiesUtil.getPropertyValue("WEATHER_TIME_BEGIN");
        String weatherTimeEnd = PropertiesUtil.getPropertyValue("WEATHER_TIME_END");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");


        System.setProperty("webdriver.chrome.driver", "E:\\tools\\selenium\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\caochf\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);//初始化浏览器

        try {

            String url = "";

            if(weatherCitys!=null&&!"".equals(weatherCitys)
                    &&weatherTimeBegin!=null&&!"".equals(weatherTimeBegin)
                    &&weatherUrl!=null&&!"".equals(weatherUrl)) {
                if (weatherTimeEnd == null || "".equals(weatherTimeEnd)) {
                    weatherTimeEnd = formatter.format(new Date());
                }

                String[] weatherCityArr = weatherCitys.split(",");
                for (String weatherCity : weatherCityArr) {
                    Date startDate = formatter.parse(weatherTimeBegin);
                    Date endDate = formatter.parse(weatherTimeEnd);
                    while (startDate.getTime() < endDate.getTime()) {
                        String curDateStr = formatter.format(startDate);
                        url = weatherUrl + "/" + weatherCity + "/" + curDateStr;

                        driver.navigate().to(url);
                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                        //获取当前浏览器的信息
                        System.out.println("Title:" + driver.getTitle());
                        System.out.println("currentUrl:" + driver.getCurrentUrl());
                        driver.close();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
