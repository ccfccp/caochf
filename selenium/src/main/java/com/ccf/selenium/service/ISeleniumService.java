package com.ccf.selenium.service;

import com.ccf.selenium.data.spiderData.SpiderOriData;
import com.ccf.selenium.data.subject.SpiderSubject;

import java.util.List;

public interface ISeleniumService {
    /**
     * 取爬取任务定义
     * @param spiderIndexUrl
     * @return
     */
    List<SpiderSubject> getSpiderSubject(String spiderIndexUrl);

    /**
     * 原始数据爬取.
     * @param subject
     * @return
     */
    List<SpiderOriData> spider(SpiderSubject subject);
}
