package com.ccf.selenium.cmd;

import com.ccf.selenium.data.spiderData.SpiderOriData;
import com.ccf.selenium.data.subject.SpiderSubject;
import com.ccf.selenium.service.ISeleniumService;
import com.ccf.selenium.service.SeleniumServiceImpl;

import java.util.List;

public class SeleniumSpider {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SeleniumSpider.class);
    private static ISeleniumService seleniumService = new SeleniumServiceImpl();
    public static void main(String[] args){
        log.info("SeleniumSpider------start!");
        totalRun(args);
        log.info("SeleniumSpider------end!");
    }

    /**
     * 整体运行方法.
     * @param args
     */
    private static void totalRun(String[] args) {
        log.info("SeleniumSpider--totalRun----------begin!");
        long startTime = System.currentTimeMillis();
        String spiderIndexUrl = "";
        // 取爬取定义
        List<SpiderSubject> spiderSubjectList = seleniumService.getSpiderSubject(spiderIndexUrl);

        for(SpiderSubject subject:spiderSubjectList){
            // 执行爬取定义
            List<SpiderOriData> spiderOriDataList = seleniumService.spider(subject);
            log.info("爬取数据展示：\n\tsubject="+subject.toString()+"  \n\tspiderOriDataList="+spiderOriDataList);
        }

        log.info("SeleniumSpider--totalRun----------end!耗时："+(System.currentTimeMillis()-startTime)+"ms!");
    }
}
