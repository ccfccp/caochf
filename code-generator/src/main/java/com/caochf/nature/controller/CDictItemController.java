package com.caochf.nature.controller;

import java.util.Arrays;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.caochf.nature.entity.CDictItemEntity;
import com.caochf.nature.service.CDictItemService;
import java.util.List;

/**
 * D. 字典项
 *
 * @author huangwenjun
 * @date 2020-07-22 10:05:51
 */
@Api(tags = "nature")
@RestController
@RequestMapping("nature/cdictitem")
public class CDictItemController {

    @Autowired
    private CDictItemService cDictItemService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "insert")
    public Object insert(CDictItemEntity cDictItem){
        return cDictItemService.insert(cDictItem);
    }

    @ApiOperation(value = "删除")
    @GetMapping(value = "delete")
    public int deleteByPrimaryKey(String key) {
        return cDictItemService.deleteByPrimaryKey(key);
    }

    @ApiOperation(value = "根据主键更新")
    @PostMapping(value = "updateByPrimaryKey")
    public int updateByPrimaryKey(CDictItemEntity cDictItem) {
        return cDictItemService.updateByPrimaryKey(cDictItem);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping(value = "selectByPrimaryKey")
    public CDictItemEntity selectByPrimaryKey(String key) {
        return cDictItemService.selectByPrimaryKey(key);
    }

    @ApiOperation(value = "条件查询")
    @GetMapping(value = "select")
    public List<CDictItemEntity> select(CDictItemEntity cDictItem) {
        return cDictItemService.select(cDictItem);
    }
}