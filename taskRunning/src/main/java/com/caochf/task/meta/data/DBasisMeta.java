package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dBasisExpression.data.DBasisExpression;
import com.inspur.credit.fda.task.dElement.data.DElement;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 标准元数据.
 */
public class DBasisMeta {
    // 标准
    private List<DBasisExpression> basisExpressionList ;
    // 要素
    private List<DElement> elementList;

    public List<DBasisExpression> getBasisExpressionList() {
        return basisExpressionList;
    }

    public void setBasisExpressionList(List<DBasisExpression> basisExpressionList) {
        this.basisExpressionList = basisExpressionList;
    }

    public List<DElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<DElement> elementList) {
        this.elementList = elementList;
    }

    // 校验信息.
    @Transient
    private CheckMsg checkMsg;
    /**
     * 校验.
     * @param reCheckFlag
     * @return
     */
    public CheckMsg check(String reCheckFlag) {
        if((reCheckFlag!=null&&"1".equals(reCheckFlag))
                ||checkMsg==null) {
            checkMsg = new CheckMsg();
            String displayObjName = "标准元数据";
            String checkFlag = "SUCCESS";
            StringBuffer warningSb = new StringBuffer("");
            StringBuffer failureSb = new StringBuffer("");

            if(basisExpressionList==null||basisExpressionList.size()==0){
                checkFlag = "FAILURE";
                failureSb.append("标准信息D_BASIS_EXPRESSION未维护\n");
            }
            if(elementList==null||elementList.size()==0){
                checkFlag = "FAILURE";
                failureSb.append("要素D_ELEMENT未维护\n");
            }

            checkMsg.setDisplayObjName(displayObjName);
            checkMsg.setCheckFlag(checkFlag);
            checkMsg.setWarningMsg(warningSb.toString());
            checkMsg.setFailureMsg(failureSb.toString());

            List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
            if(basisExpressionList!=null){
                for(DBasisExpression basisExpression:basisExpressionList){
                    childCheckMsgList.add(basisExpression.check(reCheckFlag));
                }
            }
            if(elementList!=null){
                for(DElement element:elementList){
                    childCheckMsgList.add(element.check(reCheckFlag));
                }
            }
            if(childCheckMsgList.size()>0){
                checkMsg.setChildCheckMsgList(childCheckMsgList);
                checkMsg.updateCheckFlag();
            }

        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DBasisMeta{" +
                "basisExpressionList=" + basisExpressionList +
                ", elementList=" + elementList +
                '}';
    }
}
