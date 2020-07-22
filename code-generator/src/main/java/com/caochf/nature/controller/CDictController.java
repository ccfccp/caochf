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
import com.caochf.nature.entity.CDictEntity;
import com.caochf.nature.service.CDictService;
import java.util.List;

/**
 * D. 字典
 *
 * @author huangwenjun
 * @date 2020-07-22 10:05:52
 */
@Api(tags = "nature")
@RestController
@RequestMapping("nature/cdict")
public class CDictController {

    @Autowired
    private CDictService cDictService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "insert")
    public Object insert(CDictEntity cDict){
        return cDictService.insert(cDict);
    }

    @ApiOperation(value = "删除")
    @GetMapping(value = "delete")
    public int deleteByPrimaryKey(String key) {
        return cDictService.deleteByPrimaryKey(key);
    }

    @ApiOperation(value = "根据主键更新")
    @PostMapping(value = "updateByPrimaryKey")
    public int updateByPrimaryKey(CDictEntity cDict) {
        return cDictService.updateByPrimaryKey(cDict);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping(value = "selectByPrimaryKey")
    public CDictEntity selectByPrimaryKey(String key) {
        return cDictService.selectByPrimaryKey(key);
    }

    @ApiOperation(value = "条件查询")
    @GetMapping(value = "select")
    public List<CDictEntity> select(CDictEntity cDict) {
        return cDictService.select(cDict);
    }
}