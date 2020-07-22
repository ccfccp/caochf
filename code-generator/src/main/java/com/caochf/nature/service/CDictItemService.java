package com.caochf.nature.service;

import com.caochf.nature.repository.CDictItemDao;
import com.caochf.nature.entity.CDictItemEntity;
import com.caochf.nature.service.CDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author huangwenjun
 * @date 2020-07-22 10:05:51
 */
@Service
public class CDictItemService {

    @Autowired
    private CDictItemDao cDictItemDao;

    public Object insert(CDictItemEntity cDictItem){
        return cDictItemDao.insert(cDictItem);
    }

    public int deleteByPrimaryKey(String key) {
        return cDictItemDao.deleteByPrimaryKey(key);
    }

    public int updateByPrimaryKey(CDictItemEntity cDictItem) {
        return cDictItemDao.updateByPrimaryKey(cDictItem);
    }

    public CDictItemEntity selectByPrimaryKey(String key) {
        return cDictItemDao.selectByPrimaryKey(key);
    }

    public List<CDictItemEntity> select(CDictItemEntity cDictItem) {
        return cDictItemDao.select(cDictItem);
    }
}