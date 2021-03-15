package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.util.Tools;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务元数据.
 */
public class TaskMeta {
    // 任务主信息.
    private DTaskMeta taskMeta;

    // 取数据元数据.
    private DQueryDataMeta queryDataMeta;

    // 存数据元数据
    private DStoreDataMeta storeDataMeta;

    // 处理标准元数据
    private DBasisMeta basisMeta;

    // 校验信息.
    @Transient
    private CheckMsg checkMsg;

    public DTaskMeta getTaskMeta() {
        return taskMeta;
    }

    public void setTaskMeta(DTaskMeta taskMeta) {
        this.taskMeta = taskMeta;
    }

    public DQueryDataMeta getQueryDataMeta() {
        return queryDataMeta;
    }

    public void setQueryDataMeta(DQueryDataMeta queryDataMeta) {
        this.queryDataMeta = queryDataMeta;
    }

    public DStoreDataMeta getStoreDataMeta() {
        return storeDataMeta;
    }

    public void setStoreDataMeta(DStoreDataMeta storeDataMeta) {
        this.storeDataMeta = storeDataMeta;
    }

    public DBasisMeta getBasisMeta() {
        return basisMeta;
    }

    public void setBasisMeta(DBasisMeta basisMeta) {
        this.basisMeta = basisMeta;
    }

    /**
     * 任务元数据整体校验.
     * @return
     */
    public CheckMsg check(String reCheckFlag) {
        if((reCheckFlag!=null&&"1".equals(reCheckFlag))
                ||checkMsg==null){
            checkMsg = new CheckMsg();
            String displayObjName = null;
            if(taskMeta!=null&&taskMeta.getTaskDef()!=null){
                displayObjName = taskMeta.getTaskDef().getTaskName();
            }
            if(taskMeta==null){
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg("未维护任务信息\n");
            }
            if(queryDataMeta==null){
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg((checkMsg.getFailureMsg()==null?"":checkMsg.getFailureMsg())+"未维护数据读取元数据信息\n");
            }
            if(storeDataMeta==null){
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg((checkMsg.getFailureMsg()==null?"":checkMsg.getFailureMsg())+"未维护数据存储元数据信息\n");
            }
            if(basisMeta==null){
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg((checkMsg.getFailureMsg()==null?"":checkMsg.getFailureMsg())+"未维护标准元数据信息\n");
            }
            if(!"FAILURE".equals(checkMsg.getCheckFlag())) {
                CheckMsg taskMsg = taskMeta.check(reCheckFlag);
                CheckMsg queryCheckMsg = queryDataMeta.check(reCheckFlag);
                CheckMsg storeCheckMsg = storeDataMeta.check(reCheckFlag);
                CheckMsg basisCheckMsg = basisMeta.check(reCheckFlag);

                checkMsg.setDisplayObjName(displayObjName);
                List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
                childCheckMsgList.add(taskMsg);
                childCheckMsgList.add(queryCheckMsg);
                childCheckMsgList.add(storeCheckMsg);
                childCheckMsgList.add(basisCheckMsg);
                checkMsg.setChildCheckMsgList(childCheckMsgList);
                checkMsg.updateCheckFlag();
            }
        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "TaskMeta{" +
                "taskMeta=" + taskMeta +
                ", queryDataMeta=" + queryDataMeta +
                ", storeDataMeta=" + storeDataMeta +
                ", basisMeta=" + basisMeta +
                '}';
    }
}
