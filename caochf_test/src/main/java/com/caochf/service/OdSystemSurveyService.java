package com.caochf.service;

import com.caochf.entity.OdSystemSurvey;
import com.caochf.mapper.OdSystemSurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdSystemSurveyService {
    @Autowired
    OdSystemSurveyMapper odSystemSurveyMapper;


    public String selectByPrimaryKey(String id) {
        String result = null;
        OdSystemSurvey odSystemSurvey = odSystemSurveyMapper.selectByPrimaryKey(id);
        if(odSystemSurvey!=null){
            result = odSystemSurvey.toString();
        }
        return result;
    }
}
