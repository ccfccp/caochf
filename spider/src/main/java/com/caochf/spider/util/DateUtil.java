package com.caochf.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 取前n个月的日期.
     * @return
     */
    public static Date getPreMonthDate(Date inDate, int n){
        Date preDate = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar date= Calendar.getInstance();
        date.setTime(inDate);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH)-n);
        try {
            preDate = sdf.parse(sdf.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return preDate;
    }
}
