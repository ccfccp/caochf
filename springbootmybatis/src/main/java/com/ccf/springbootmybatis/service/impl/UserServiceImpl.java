package com.ccf.springbootmybatis.service.impl;

import com.ccf.springbootmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public void addUser(Users userInfo) {
        usersMapper.insertUser(userInfo);

    }
}
