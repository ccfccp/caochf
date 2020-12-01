package com.caochf.controller;

import com.caochf.service.OdSystemSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/odSystemSurvey")
public class OdSystemSurveyController {

    @Autowired
    private OdSystemSurveyService odSystemSurveyService;

    @RequestMapping("getOdSystemSurvey/{id}")
    @ResponseBody
    public Map<String,String> getOdSystemSurvey(@PathVariable String id){
        Map<String,String> map = new HashMap<>();
        map.put("mm",odSystemSurveyService.selectByPrimaryKey(id));
        return map;
    }
}
