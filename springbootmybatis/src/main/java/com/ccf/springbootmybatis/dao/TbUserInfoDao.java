package com.ccf.springbootmybatis.dao;


import com.ccf.springbootmybatis.data.TbUserInfo;

public interface TbUserInfoDao {
    int insert(TbUserInfo record);

    int insertSelective(TbUserInfo record);
}