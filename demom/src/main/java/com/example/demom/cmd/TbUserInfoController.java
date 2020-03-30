package com.example.demom.cmd;

import com.example.demom.entity.TbUserInfo;
import com.example.demom.mapper.TbUserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class TbUserInfoController {
    @Autowired
    private TbUserInfoDao tbUserInfoDao;
    @RequestMapping("/findAll")
    public List<TbUserInfo> findAll(){
        List<TbUserInfo> list = tbUserInfoDao.findAll();
        return list;
    }
}
