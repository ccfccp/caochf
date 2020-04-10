package com.ccf.selenium.data.subject;

import com.ccf.selenium.data.defData.DataSort;

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
    /**
     * 爬取数据分类信息.
     */
    private DataSort dataSort;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getOriUrl() {
        return oriUrl;
    }

    public void setOriUrl(String oriUrl) {
        this.oriUrl = oriUrl;
    }

    public List<String> getNoSpiderUrlList() {
        return noSpiderUrlList;
    }

    public void setNoSpiderUrlList(List<String> noSpiderUrlList) {
        this.noSpiderUrlList = noSpiderUrlList;
    }

    public Map<String, String> getCompletSpiderUrlMap() {
        return completSpiderUrlMap;
    }

    public void setCompletSpiderUrlMap(Map<String, String> completSpiderUrlMap) {
        this.completSpiderUrlMap = completSpiderUrlMap;
    }

    public String getSpiderStartTime() {
        return spiderStartTime;
    }

    public void setSpiderStartTime(String spiderStartTime) {
        this.spiderStartTime = spiderStartTime;
    }

    public String getSpiderEndTime() {
        return spiderEndTime;
    }

    public void setSpiderEndTime(String spiderEndTime) {
        this.spiderEndTime = spiderEndTime;
    }

    public Conditions getAddUrlCondition() {
        return addUrlCondition;
    }

    public void setAddUrlCondition(Conditions addUrlCondition) {
        this.addUrlCondition = addUrlCondition;
    }

    public Conditions getEndCondition() {
        return endCondition;
    }

    public void setEndCondition(Conditions endCondition) {
        this.endCondition = endCondition;
    }

    public Conditions getRemoveCondition() {
        return removeCondition;
    }

    public void setRemoveCondition(Conditions removeCondition) {
        this.removeCondition = removeCondition;
    }

    public DataSort getDataSort() {
        return dataSort;
    }

    public void setDataSort(DataSort dataSort) {
        this.dataSort = dataSort;
    }
}
