package com.caochf.spider.weather;

import com.caochf.spider.util.DateUtil;
import com.caochf.spider.util.HtmlutilUtil;
import com.caochf.spider.util.PropertiesUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WeatherSpider {
    public static void main(String[] args) throws ParseException {
        String url = "";
        String weatherUrl = PropertiesUtil.getPropertyValue("WEATHER_URL");
        String weatherCitys = PropertiesUtil.getPropertyValue("WEATHER_CITYS");
        String weatherTimeBegin = PropertiesUtil.getPropertyValue("WEATHER_TIME_BEGIN");
        String weatherTimeEnd = PropertiesUtil.getPropertyValue("WEATHER_TIME_END");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        if(weatherCitys!=null&&!"".equals(weatherCitys)
                &&weatherTimeBegin!=null&&!"".equals(weatherTimeBegin)
                &&weatherUrl!=null&&!"".equals(weatherUrl)){
            if(weatherTimeEnd==null||"".equals(weatherTimeEnd)){
                weatherTimeEnd = formatter.format(new Date());
            }

            String[] weatherCityArr = weatherCitys.split(",");
            for(String weatherCity:weatherCityArr){
                Date startDate = formatter.parse(weatherTimeBegin);
                Date endDate = formatter.parse(weatherTimeEnd);
                while(startDate.getTime()<endDate.getTime()){
                    String curDateStr = formatter.format(startDate);
                    url = weatherUrl+"/"+weatherCity+"/"+curDateStr;
                    try {
                        Map<String,String> webContentMap = HtmlutilUtil.getStrByUrl(url);
                        System.out.println(webContentMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        startDate = DateUtil.getPreMonthDate(startDate,1);
                    }
                }
            }
        }

    }
}
