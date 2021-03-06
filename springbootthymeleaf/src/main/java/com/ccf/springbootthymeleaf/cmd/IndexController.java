package com.ccf.springbootthymeleaf.cmd;

import com.ccf.springbootthymeleaf.data.User_N;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/home")
    public String index(Model model) {
        User_N user = new User_N();
        user.setUsername("jack");
        user.setPassword("112233");
        user.setHobbies(Arrays.asList(new String[]{"singing", "dancing", "football"}));
        Map<String, String> maps = new HashMap<>();
        maps.put("1", "o");
        maps.put("2", "g");
        maps.put("3", "a");
        maps.put("4", "j");
        user.setSecrets(maps);
        model.addAttribute("user", user);
        return "test_thymeleaf";
    }
    @GetMapping("/testjsgrid")
    public String jsgrid(Model model){
        return "testjsgrid/index";
    }

    @GetMapping("/testjqgrid")
    public String jqgrid(Model model){
        return "/testjqgrid/testjqgrid";
    }
}