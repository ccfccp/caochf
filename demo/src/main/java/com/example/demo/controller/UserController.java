package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    @ResponseBody
    public Map<String,String> GetUser(@PathVariable int id){
        Map<String,String> map = new HashMap<>();
        map.put("mm",userService.Sel(id).toString());
        return map;
    }
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getAllUser(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userlist",userList);
        return "index";
    }
}