package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dRelStoreObj.data.DRelStoreObj;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储对象关联信息元数据.
 */
public class DStoreRelMeta {
    // 主从对象关系.
    private DRelStoreObj rel;
    // 从对象
    private DStoreObjMeta secondaryStoreObj;

    public DRelStoreObj getRel() {
        return rel;
    }

    public void setRel(DRelStoreObj rel) {
        this.rel = rel;
    }

    public DStoreObjMeta getSecondaryStoreObj() {
        return secondaryStoreObj;
    }

    public void setSecondaryStoreObj(DStoreObjMeta secondaryStoreObj) {
        this.secondaryStoreObj = secondaryStoreObj;
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
            String displayObjName = "存储从对象";
            String checkFlag = "SUCCESS";
            StringBuffer warningSb = new StringBuffer("");
            StringBuffer failureSb = new StringBuffer("");

            if(rel==null){
                checkFlag = "FAILURE";
                failureSb.append("存储主从对象关系未维护\n");
            }
            if(secondaryStoreObj==null){
                checkFlag = "FAILURE";
                failureSb.append("存储从对象未维护\n");
            }
            checkMsg.setDisplayObjName(displayObjName);
            checkMsg.setCheckFlag(checkFlag);
            checkMsg.setWarningMsg(warningSb.toString());
            checkMsg.setFailureMsg(failureSb.toString());

            List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
            if(rel!=null) {
                childCheckMsgList.add(rel.check(reCheckFlag));
            }
            if(secondaryStoreObj!=null){
                childCheckMsgList.add(secondaryStoreObj.check(reCheckFlag));
            }

            if(childCheckMsgList!=null&&childCheckMsgList.size()>0){
                checkMsg.setChildCheckMsgList(childCheckMsgList);
                checkMsg.updateCheckFlag();
            }
        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DStoreRelMeta{" +
                "rel=" + rel +
                ", secondaryStoreObj=" + secondaryStoreObj +
                '}';
    }
}
