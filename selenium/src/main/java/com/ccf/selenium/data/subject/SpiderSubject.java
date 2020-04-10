package com.ccf.selenium.data.subject;

import java.util.List;
import java.util.Map;

/**
 * 爬取动作定义.
 */
public class SpiderSubject {
    /**
     * 唯一标识.
     */
    private String subjectId;
    /**
     * 初始URL.
     */
    private String oriUrl;
    /**
     * 未爬取url.
     */
    private List<String> noSpiderUrlList;
    /**
     * 已完成爬取的url.
     *  Map<status,url>
     */
    private Map<String,String> completSpiderUrlMap;
    /**
     * 爬取开始时间.
     */
    private String spiderStartTime;
    /**
     * 爬取结束时间.
     */
    private String spiderEndTime;
    /**
     * 加入爬取条件.
     */
    private Conditions addUrlCondition;
    /**
     * 结束条件.
     */
    private Conditions endCondition;
    /**
     * 排除条件.
     */
    private Conditions removeCondition;


}
