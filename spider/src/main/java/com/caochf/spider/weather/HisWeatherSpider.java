package com.caochf.spider.weather;

import com.caochf.spider.util.DaoUtil;
import com.caochf.spider.util.IDGenerate;
import com.caochf.spider.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HisWeatherSpider {
    public static void main(String[] args) throws SQLException {

        String weatherUrl = PropertiesUtil.getPropertyValue("WEATHER_URL");
        String weatherRootUrl = PropertiesUtil.getPropertyValue("WEATHER_ROOT_URL");
        String weatherCitys = PropertiesUtil.getPropertyValue("WEATHER_CITYS");
        String weatherTimeBegin = PropertiesUtil.getPropertyValue("WEATHER_TIME_BEGIN");
        String weatherTimeEnd = PropertiesUtil.getPropertyValue("WEATHER_TIME_END");

        String dbDriver = PropertiesUtil.getPropertyValue("WEATHER_DB_DRIVER");
        String dbUrl = PropertiesUtil.getPropertyValue("WEATHER_DB_URL");
        String dbUsername = PropertiesUtil.getPropertyValue("WEATHER_DB_USERNAME");
        String dbPasswd = PropertiesUtil.getPropertyValue("WEATHER_DB_PASSWD");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");


        System.setProperty("webdriver.chrome.driver", "E:\\tools\\selenium\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\caochf\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);//初始化浏览器

        Connection conn = null;
        try {
            conn = DaoUtil.getConnection(dbDriver,dbUrl,dbUsername,dbPasswd);
            if(weatherCitys!=null&&!"".equals(weatherCitys)
                    &&weatherTimeBegin!=null&&!"".equals(weatherTimeBegin)
                    &&weatherUrl!=null&&!"".equals(weatherUrl)) {
                if (weatherTimeEnd == null || "".equals(weatherTimeEnd)) {
                    weatherTimeEnd = formatter.format(new Date());
                }

                String[] weatherCityArr = weatherCitys.split(",");
                for (String weatherCity : weatherCityArr) {
                    List<String> innerUrlList = new ArrayList<String>();
                    String url = weatherUrl.replace("::city::",weatherCity);
                    driver.navigate().to(url);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    //获取当前浏览器的信息
                    System.out.println("Title:" + driver.getTitle());
                    System.out.println("currentUrl:" + driver.getCurrentUrl());

                    String hisOptionXPath = "/html/body/div[8]/div[1]/div[5]/div/div[1]/div/select";
                    WebElement hisSelect = getElement(driver,hisOptionXPath);
                    System.out.println(hisSelect.getTagName());
                    Select select = new Select(hisSelect);
                    if(select!=null){
                        List<WebElement> optionElementList = select.getOptions();
                        for(WebElement option:optionElementList){
                            System.out.println("option:"+option.getText()+","+option.getAttribute("value"));
                            String dateValue = option.getAttribute("value");
                            if(dateValue!=null&&!"".equals(dateValue)
                                    &&!innerUrlList.contains(weatherRootUrl+"/"+weatherCity+"/"+dateValue+".html")){
                                innerUrlList.add(weatherRootUrl+"/"+weatherCity+"/"+dateValue+".html");
                            }
                        }
                    }

                    if(innerUrlList!=null&&innerUrlList.size()>0){
                        List<Map<String,String>> weatherDataList = new ArrayList<Map<String,String>>();
                        for(String innerUrl:innerUrlList){
                            List<Map<String,String>> hisWeatherList = getHisWeatherDatas(innerUrl,weatherCity);
                            if(hisWeatherList!=null&&hisWeatherList.size()>0){
                                saveDatas2DB(conn,hisWeatherList,innerUrl);
                            }
                        }
                    }




                    /*Date startDate = formatter.parse(weatherTimeBegin);
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
                    }*/



                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
            if(conn!=null){
                conn.close();
            }
        }
    }

    /**
     * 保存数据.
     * @param conn
     * @param hisWeatherList
     * @param innerUrl
     */
    private static void saveDatas2DB(Connection conn, List<Map<String, String>> hisWeatherList, String innerUrl) throws SQLException {
        PreparedStatement pst = null;
        String sql = "insert into his_weather (ID,CITY,WEATHER_DATE,TEMPERATURE_HIGH,TEMPERATURE_LOW,W_WEATHER,WIND_SIGN,WEATHER_POINT,spider_url,C_WEATHER_DATE,C_WEATHER_WEEK) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";

        if(hisWeatherList!=null&&hisWeatherList.size()>0){
            try {
                String delSql = "delete from his_weather where spider_url=?";
                pst = conn.prepareStatement(delSql);
                pst.setString(1,innerUrl);
                pst.executeUpdate();
                pst.close();

                pst = conn.prepareStatement(sql);
                Map<String,String> weatherData = null;
                for(int i=0;i<hisWeatherList.size();i++){
                    weatherData = hisWeatherList.get(i);
                    pst.setString(1, IDGenerate.getUUID32());
                    pst.setString(2, weatherData.get("CITY"));
                    pst.setString(3, weatherData.get("WEATHER_DATE"));
                    pst.setString(4, weatherData.get("TEMPERATURE_HIGH"));
                    pst.setString(5, weatherData.get("TEMPERATURE_LOW"));
                    pst.setString(6, weatherData.get("W_WEATHER"));
                    pst.setString(7, weatherData.get("WIND_SIGN"));
                    pst.setString(8, weatherData.get("WEATHER_POINT"));
                    pst.setString(9, innerUrl);
                    pst.setString(10,weatherData.get("C_WEATHER_DATE"));
                    pst.setString(11,weatherData.get("C_WEATHER_WEEK"));
                    pst.addBatch();
                    if(i%100==99||i==hisWeatherList.size()-1){
                        pst.executeBatch();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(pst!=null){
                    pst.close();
                }
            }

        }
    }

    /**
     * 读取url中的历史天气数据.
     * @param url
     * @param weatherCity
     * @return
     */
    private static List<Map<String, String>> getHisWeatherDatas(String url, String weatherCity) {
        List<Map<String,String>> weatherDataList = new ArrayList<Map<String,String>>();
        System.setProperty("webdriver.chrome.driver", "E:\\tools\\selenium\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\caochf\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);//初始化浏览器

        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);

        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='tian_three']/ul[@class='thrui']/li"));
        if(elementList!=null&&elementList.size()>0){
            for(WebElement element:elementList){
                System.out.println(element.getTagName());
                if(element!=null&&"li".equals(element.getTagName())) {
                    List<WebElement> oneDataElementList = element.findElements(By.xpath("./div"));

                    if(oneDataElementList!=null&&oneDataElementList.size()>=6){
                        Map<String, String> dataMap = new HashMap<String,String>();
                        dataMap.put("CITY",weatherCity);
                        dataMap.put("WEATHER_DATE",oneDataElementList.get(0).getText());
                        dataMap.put("TEMPERATURE_HIGH",oneDataElementList.get(1).getText());
                        dataMap.put("TEMPERATURE_LOW",oneDataElementList.get(2).getText());
                        dataMap.put("W_WEATHER",oneDataElementList.get(3).getText());
                        dataMap.put("WIND_SIGN",oneDataElementList.get(4).getText());
                        dataMap.put("WEATHER_POINT",oneDataElementList.get(5).getText());
                        String weatherDataStr = oneDataElementList.get(0).getText();
                        String weatherDate = null;
                        String weatherWeek = null;
                        if(weatherDataStr!=null&&!"".equals(weatherDataStr)&&weatherDataStr.length()>10){
                            weatherDate = weatherDataStr.substring(0,10);
                            weatherWeek = weatherDataStr.substring(10,weatherDataStr.length());
                        }
                        dataMap.put("C_WEATHER_DATE",weatherDate);
                        dataMap.put("C_WEATHER_WEEK",weatherWeek);
                        weatherDataList.add(dataMap);
                    }
                }

            }
        }
        driver.close();
        return weatherDataList;
    }

    /**
     * 通过xPath取WebElement.
     * @param driver
     * @param xPath
     * @return
     */
    private static WebElement getElement(WebDriver driver, String xPath) {
        WebElement webElement = null;
        webElement = driver.findElement(By.xpath(xPath));
        return webElement;
    }

    /**
     * 获取全部的链接信息.
     * @param linkList
     * @return
     */
    private static List<String> findHref(List<WebElement> linkList) {
        List<String> hrefList = new ArrayList<String>();
        if(linkList!=null&&linkList.size()>0){
//            for(){
//
//            }
        }
        return hrefList;
    }
}
