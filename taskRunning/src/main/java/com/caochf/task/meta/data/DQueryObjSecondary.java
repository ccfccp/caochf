package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dQueryObjDef.data.DQueryObjDef;
import com.inspur.credit.fda.task.dQueryObjRel.data.DQueryObjRel;
import com.inspur.credit.fda.task.tQueryObjUpdate.data.DQueryObjUpdate;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据读取从对象封装.
 */
public class DQueryObjSecondary {
    // 与主对象关联信息
    private DQueryObjRel rel;
    // 从对象元数据.
    private DQueryObjDef secondaryObj;
    // 从对象的数据更新设置
    private List<DQueryObjUpdate> secondaryObjUpdateList;
    // 从对象的从对象信息,只有在本对象与主对象为1-1的情况下可以获取.
    private List<DQueryObjSecondary> subsecondaryList;

    public List<DQueryObjSecondary> getSubsecondaryList() {
        return subsecondaryList;
    }

    public void setSubsecondaryList(List<DQueryObjSecondary> subsecondaryList) {
        this.subsecondaryList = subsecondaryList;
    }

    public DQueryObjRel getRel() {
        return rel;
    }

    public void setRel(DQueryObjRel rel) {
        this.rel = rel;
    }

    public DQueryObjDef getSecondaryObj() {
        return secondaryObj;
    }

    public void setSecondaryObj(DQueryObjDef secondaryObj) {
        this.secondaryObj = secondaryObj;
    }

    public List<DQueryObjUpdate> getSecondaryObjUpdateList() {
        return secondaryObjUpdateList;
    }

    public void setSecondaryObjUpdateList(List<DQueryObjUpdate> secondaryObjUpdateList) {
        this.secondaryObjUpdateList = secondaryObjUpdateList;
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
            String objName = "数据读取子对象";
            String checkFlag = "SUCCESS";
            StringBuffer warningSb = new StringBuffer("");
            StringBuffer failureSb = new StringBuffer("");

            List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
            if(rel==null){
                checkFlag = "FAILURE";
                failureSb.append("关系信息未维护！\n");
            }else{
                childCheckMsgList.add(rel.check(reCheckFlag));
            }
            if(secondaryObj==null){
                checkFlag = "FAILURE";
                failureSb.append("从对象信息未维护！\n");
            }else{
                childCheckMsgList.add(secondaryObj.check(reCheckFlag));
            }
            if(secondaryObjUpdateList==null||secondaryObjUpdateList.size()==0){
                if(!"FAILURE".equals(checkFlag)){
                    checkFlag = "WARNING";
                }
                warningSb.append("从对象更新信息未维护！\n");
            }else{
                for(DQueryObjUpdate columnUpdateSetting:secondaryObjUpdateList){
                    childCheckMsgList.add(columnUpdateSetting.check(reCheckFlag));
                }
            }
            checkMsg.setDisplayObjName(objName);
            checkMsg.setCheckFlag(checkFlag);
            checkMsg.setFailureMsg(failureSb.toString());
            checkMsg.setWarningMsg(warningSb.toString());
            checkMsg.setChildCheckMsgList(childCheckMsgList);
        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DQueryObjSecondary{" +
                "rel=" + rel +
                ", secondaryObj=" + secondaryObj +
                ", secondaryObjUpdateList=" + secondaryObjUpdateList +
                '}';
    }
}
