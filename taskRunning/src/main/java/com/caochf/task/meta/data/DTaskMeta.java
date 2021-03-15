package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dTaskDef.data.DTaskDef;
import org.loushang.next.dao.Transient;

public class DTaskMeta {
    // 任务信息.
    private DTaskDef taskDef;
    // 校验信息.
    @Transient
    private CheckMsg checkMsg;
    public DTaskDef getTaskDef() {
        return taskDef;
    }

    public void setTaskDef(DTaskDef taskDef) {
        this.taskDef = taskDef;
    }

    /**
     * 校验.
     * @param reCheckFlag
     * @return
     */
    public CheckMsg check(String reCheckFlag) {
        if((reCheckFlag!=null&&"1".equals(reCheckFlag))
                ||checkMsg==null) {
            checkMsg = new CheckMsg();
            String checkFlag = "SUCCESS";
            StringBuffer warningSb = new StringBuffer("");
            StringBuffer failureSb = new StringBuffer("");
            if(taskDef==null||taskDef.getTaskId()==null){
                checkFlag = "FAILURE";
                failureSb.append("任务信息未定义\n");
            }else{
                checkMsg.setDisplayObjName(taskDef.getTaskName());
                if(taskDef.getTaskName()==null||"".equals(taskDef.getTaskName())){
                    checkFlag = "FAILURE";
                    failureSb.append("任务名称未维护\n");
                }
                if(taskDef.getYear()==null||"".equals(taskDef.getYear())){
                    checkFlag = "FAILURE";
                    failureSb.append("标准年度未维护\n");
                }
                if(taskDef.getTaskLevel()==null||"".equals(taskDef.getTaskLevel())){
                    checkFlag = "FAILURE";
                    failureSb.append("任务分级未维护\n");
                }
                if(taskDef.getTaskObjId()==null||"".equals(taskDef.getTaskObjId())){
                    checkFlag = "FAILURE";
                    failureSb.append("任务对象未维护\n");
                }
                if(taskDef.getStoreObjId()==null||"".equals(taskDef.getStoreObjId())){
                    checkFlag = "FAILURE";
                    failureSb.append("存储对象未维护\n");
                }
                if(taskDef.getTaskType()==null||"".equals(taskDef.getTaskType())){
                    if(!"FAILURE".equals(checkFlag)) {
                        checkFlag = "WARNING";
                    }
                    warningSb.append("任务分类未维护\n");
                }
                if(taskDef.getBasisUseType()==null||"".equals(taskDef.getBasisUseType())){
                    checkFlag = "FAILURE";
                    failureSb.append("依据使用方式未维护\n");
                }
            }
            checkMsg.setCheckFlag(checkFlag);
            checkMsg.setWarningMsg(warningSb.toString());
            checkMsg.setFailureMsg(failureSb.toString());
        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DTaskMeta{" +
                "taskDef=" + taskDef +
                '}';
    }
}
