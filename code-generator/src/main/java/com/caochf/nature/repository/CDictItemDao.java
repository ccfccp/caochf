package com.caochf.nature.repository;

import com.caochf.nature.entity.CDictItemEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * D. 字典项
 * 
 * @author huangwenjun
 * @date 2020-07-22 10:05:51
 *
 * 接口用法大全。
 * https://mapperhelper.github.io/all/
 */
@Repository
public interface CDictItemDao extends Mapper<CDictItemEntity> {
	
}
