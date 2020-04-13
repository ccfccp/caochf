package com.ccf.selenium.service;

import com.ccf.selenium.data.spiderData.SpiderOriData;
import com.ccf.selenium.data.subject.SpiderSubject;

import java.util.ArrayList;
import java.util.List;

public class SeleniumServiceImpl implements ISeleniumService{

    /**
     * 取爬取任务定义
     *
     * @param spiderIndexUrl
     * @return
     */
    @Override
    public List<SpiderSubject> getSpiderSubject(String spiderIndexUrl) {
        List<SpiderSubject> spiderSubjectList = new ArrayList<SpiderSubject>();
        SpiderSubject spiderSubject = null;

        return spiderSubjectList;
    }

    /**
     * 原始数据爬取.
     *
     * @param subject
     * @return
     */
    @Override
    public List<SpiderOriData> spider(SpiderSubject subject) {
        return null;
    }
}
