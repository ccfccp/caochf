package com.inspur.credit.fda.schedule.task.data;

import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储对象整体信息元数据.
 */
public class DStoreDataMeta {
    // 存储主对象整体信息.
    private DStoreObjMeta primaryStoreObjMate;
    // 存储对象关联信息.
    private List<DStoreRelMeta> secondaryStoreRelMetaList;

    public DStoreObjMeta getPrimaryStoreObjMate() {
        return primaryStoreObjMate;
    }

    public void setPrimaryStoreObjMate(DStoreObjMeta primaryStoreObjMate) {
        this.primaryStoreObjMate = primaryStoreObjMate;
    }

    public List<DStoreRelMeta> getSecondaryStoreRelMetaList() {
        return secondaryStoreRelMetaList;
    }

    public void setSecondaryStoreRelMetaList(List<DStoreRelMeta> secondaryStoreRelMetaList) {
        this.secondaryStoreRelMetaList = secondaryStoreRelMetaList;
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
            if(primaryStoreObjMate==null||primaryStoreObjMate.getStoreObjDef()==null||primaryStoreObjMate.getStoreObjDef().getStoreObjId()==null){
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg("主存储对象未定义\n");
                checkMsg.setDisplayObjName("存储对象");
            }else{
                checkMsg = primaryStoreObjMate.check(reCheckFlag);
                List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
                if(secondaryStoreRelMetaList!=null){
                    for(DStoreRelMeta storeRelMeta:secondaryStoreRelMetaList){
                        childCheckMsgList.add(storeRelMeta.check(reCheckFlag));
                    }
                }
                checkMsg.setChildCheckMsgList(childCheckMsgList);
                checkMsg.updateCheckFlag();
            }
        }
        return checkMsg;
    }

    @Override
    public String toString() {
        return "DStoreDataMeta{" +
                "primaryStoreObjMate=" + primaryStoreObjMate +
                ", secondaryStoreRelMetaList=" + secondaryStoreRelMetaList +
                '}';
    }
}
