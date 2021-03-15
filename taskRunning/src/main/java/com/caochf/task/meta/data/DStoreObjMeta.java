package com.inspur.credit.fda.schedule.task.data;

import com.inspur.credit.fda.task.dStoreColumnDef.data.DStoreColumnDef;
import com.inspur.credit.fda.task.dStoreObjDef.data.DStoreObjDef;
import com.inspur.credit.fda.task.dStoreTableUniColumn.data.DStoreTableUniColumn;
import org.loushang.next.dao.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储对象元数据.
 */
public class DStoreObjMeta {
    // 存储对象定义
    private DStoreObjDef storeObjDef;
    // 对象字段信息.
    private List<DStoreColumnDef> storeColumnDefList;
    // 唯一性约束对应字段
    private List<DStoreTableUniColumn> uniColumnList;

    public DStoreObjDef getStoreObjDef() {
        return storeObjDef;
    }

    public void setStoreObjDef(DStoreObjDef storeObjDef) {
        this.storeObjDef = storeObjDef;
    }

    public List<DStoreColumnDef> getStoreColumnDefList() {
        return storeColumnDefList;
    }

    public void setStoreColumnDefList(List<DStoreColumnDef> storeColumnDefList) {
        this.storeColumnDefList = storeColumnDefList;
    }

    public List<DStoreTableUniColumn> getUniColumnList() {
        return uniColumnList;
    }

    public void setUniColumnList(List<DStoreTableUniColumn> uniColumnList) {
        this.uniColumnList = uniColumnList;
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
            checkMsg.setCheckFlag("SUCCESS");
            if(storeObjDef==null){
                checkMsg.setDisplayObjName("D_STORE_OBJ_DEF");
                checkMsg.setCheckFlag("FAILURE");
                checkMsg.setFailureMsg("D_STORE_OBJ_DEF未维护\n");
            }else{
                checkMsg = storeObjDef.check(reCheckFlag);
                List<CheckMsg> childCheckMsgList = new ArrayList<CheckMsg>();
                if(storeColumnDefList==null||storeColumnDefList.size()==0){
                    checkMsg.setCheckFlag("FAILURE");
                    checkMsg.setFailureMsg((checkMsg.getFailureMsg()==null?"":checkMsg.getFailureMsg())+"D_STORE_COLUMN_DEF未维护\n");
                }else {// 存储字段校验
                    for(DStoreColumnDef storeColumnDef:storeColumnDefList) {
                        childCheckMsgList.add(storeColumnDef.check(reCheckFlag));
                    }
                }
                if(uniColumnList==null||uniColumnList.size()==0){
                    if(!"FAILURE".equals(checkMsg.getCheckFlag())){
                        checkMsg.setCheckFlag("WARNING");
                    }
                    checkMsg.setWarningMsg(checkMsg.getWarningMsg()+"D_STORE_TABLE_UNI_COLUMN未维护\n");
                }else {// 存储唯一性约束校验.
                    for(DStoreTableUniColumn storeTableUniColumn:uniColumnList){
                        childCheckMsgList.add(storeTableUniColumn.check(reCheckFlag));
                    }
                }
                checkMsg.setChildCheckMsgList(childCheckMsgList);

                if(getPrimaryIdColumnDef()==null){
                    checkMsg.setCheckFlag("FAILURE");
                    checkMsg.setFailureMsg(checkMsg.getFailureMsg()+"未设置主键字段\n");
                }

                checkMsg.updateCheckFlag();
            }
        }
        return checkMsg;
    }

    /**
     * 取对象主键列.
     * @return
     */
    public DStoreColumnDef getPrimaryIdColumnDef() {
        DStoreColumnDef primaryIdColumnDef = null;
        if(storeColumnDefList!=null&&storeColumnDefList.size()>0){
            for(DStoreColumnDef columnDef:storeColumnDefList){
                if(columnDef!=null&&"Y".equals(columnDef.getIsPrimary())){
                    primaryIdColumnDef = columnDef;
                    break;
                }
            }
        }
        return primaryIdColumnDef;
    }

    @Override
    public String toString() {
        return "DStoreObjMeta{" +
                "storeObjDef=" + storeObjDef +
                ", storeColumnDefList=" + storeColumnDefList +
                ", uniColumnList=" + uniColumnList +
                '}';
    }
}
