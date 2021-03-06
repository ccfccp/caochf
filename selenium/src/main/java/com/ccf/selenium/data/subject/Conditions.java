package com.ccf.selenium.data.subject;

import java.util.List;

/**
 * 条件集合定义.
 */
public class Conditions {
    /**
     * 条件集合.
     */
    private List<Condition> conditionList;
    /**
     * 条件之间的关系：AND/OR
     */
    private String conditionRelationOpr;

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getConditionRelationOpr() {
        return conditionRelationOpr;
    }

    public void setConditionRelationOpr(String conditionRelationOpr) {
        this.conditionRelationOpr = conditionRelationOpr;
    }
}
