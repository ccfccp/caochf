package com.example.demom.mapper;


import com.example.demom.entity.TbUserInfo;

import java.util.List;

public interface TbUserInfoDao {
    int insert(TbUserInfo record);

    int insertSelective(TbUserInfo record);

    List<TbUserInfo> findAll();
}