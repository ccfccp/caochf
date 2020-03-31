package com.ccf.springbootmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoCommand {
    @RequestMapping(value="/say",method = RequestMethod.GET)
    @ResponseBody
    public Map sayHello(@PathVariable String name){
        Map map = new HashMap<>();
        map.put("msg","hello "+name);
        return map;
    }
}
