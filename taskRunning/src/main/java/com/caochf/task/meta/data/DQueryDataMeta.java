package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dQueryObjDef.data.DQueryObjDef;
import com.inspur.credit.fda.task.tQueryObjUpdate.data.DQueryObjUpdate;
import com.inspur.credit.fda.util.Tools;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据读取元数据配置dataBean.
 */
public class DQueryDataMeta {
    // 主对象
    private DQueryObjDef primaryObj;
    // ------主对象的数据更新设置
    private List<DQueryObjUpdate> primaryObjUpdateList;
    // 从对象，包括关联信息.
    private List<DQueryObjSecondary> secondaryList;

    // 校验信息.
    @Transient
    private CheckMsg checkMsg;

    public DQueryObjDef getPrimaryObj() {
        return primaryObj;
    }

    public void setPrimaryObj(DQueryObjDef primaryObj) {
        this.primaryObj = primaryObj;
    }

    public List<DQueryObjSecondary> getSecondaryList() {
        return secondaryList;
    }

    public void setSecondaryList(List<DQueryObjSecondary> secondaryList) {
        this.secondaryList = secondaryList;
    }

    public List<DQueryObjUpdate> getPrimaryObjUpdateList() {
        return primaryObjUpdateList;
    }

    public void setPrimaryObjUpdateList(List<DQueryObjUpdate> primaryObjUpdateList) {
        this.primaryObjUpdateList = primaryObjUpdateList;
    }

    /**
     * 元数据校验.
     * @param reCheckFlag
     * @return
     */
    public CheckMsg check(String reCheckFlag) {
        if((reCheckFlag!=null&&"1".equals(reCheckFlag))
                ||checkMsg==null) {
            checkMsg = new CheckMsg();
            String objName = null;
            String checkFlag = "SUCCESS";
            StringBuffer warningSb = new StringBuffer("");
            StringBuffer failureSb = new StringBuffer("");

            if(primaryObj==null||primaryObj.getObjId()==null||"".equals(primaryObj.getObjId())) {
                checkFlag = "FAILURE";
                objName = "数据获取对象";
                failureSb.append("数据获取对象未定义/n");
                checkMsg.setCheckFlag(checkFlag);
                checkMsg.setDisplayObjName(objName);
                checkMsg.setFailureMsg(failureSb.toString());
                checkMsg.setWarningMsg(failureSb.toString());
            }else{
                checkMsg = primaryObj.check(reCheckFlag);
                if(primaryObjUpdateList==null||primaryObjUpdateList.size()==0){
                    if(!"FAILURE".equals(checkFlag)){
                        checkFlag = "WARNING";
                    }
                    warningSb.append("主对象更新信息未维护！\n");
                }else{
                    List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
                    for(DQueryObjUpdate columnUpdateSetting:primaryObjUpdateList){
                        childCheckMsgList.add(columnUpdateSetting.check(reCheckFlag));
                    }
                    checkMsg.setChildCheckMsgList(childCheckMsgList);
                }
                if(secondaryList==null||secondaryList.size()==0){
                    if(!"FAILURE".equals(checkFlag)) {
                        checkFlag = "WARNING";
                        if(!"FAILURE".equals(checkMsg.getCheckFlag())){
                            checkMsg.setCheckFlag(checkFlag);
                        }
                    }
                    warningSb.append("数据获取从对象未定义/n");
                    checkMsg.setWarningMsg(checkMsg.getWarningMsg()+warningSb.toString());
                }else {
                    List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
                    for (DQueryObjSecondary secondary : secondaryList) {
                        childCheckMsgList.add(secondary.check(reCheckFlag));
                    }
                    checkMsg.setChildCheckMsgList(childCheckMsgList);
                }
                // 更新主提示信息的标记信息.
                checkMsg.updateCheckFlag();
            }

        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DQueryDataMeta{" +
                "primaryObj=" + primaryObj +
                ", primaryObjUpdateList=" + primaryObjUpdateList +
                ", secondaryList=" + secondaryList +
                '}';
    }
}
