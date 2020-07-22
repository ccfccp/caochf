package com.caochf.nature.service;

import com.caochf.nature.repository.CDictDao;
import com.caochf.nature.entity.CDictEntity;
import com.caochf.nature.service.CDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author huangwenjun
 * @date 2020-07-22 10:05:52
 */
@Service
public class CDictService {

    @Autowired
    private CDictDao cDictDao;

    public Object insert(CDictEntity cDict){
        return cDictDao.insert(cDict);
    }

    public int deleteByPrimaryKey(String key) {
        return cDictDao.deleteByPrimaryKey(key);
    }

    public int updateByPrimaryKey(CDictEntity cDict) {
        return cDictDao.updateByPrimaryKey(cDict);
    }

    public CDictEntity selectByPrimaryKey(String key) {
        return cDictDao.selectByPrimaryKey(key);
    }

    public List<CDictEntity> select(CDictEntity cDict) {
        return cDictDao.select(cDict);
    }
}