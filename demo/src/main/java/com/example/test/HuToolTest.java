package com.example.test;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;

public class HuToolTest {

    public static void main(String[] args){
//        ImgUtil.scale(FileUtil.file("F:\\work\\浪潮\\本部工作\\曹成峰\\身份证\\身份证.jpg"), FileUtil.file("F:\\work\\浪潮\\本部工作\\曹成峰\\身份证\\rrr.jpg"),0.5f);
//        ImgUtil.gray(FileUtil.file("F:\\work\\浪潮\\本部工作\\曹成峰\\身份证\\身份证.jpg"), FileUtil.file("F:\\work\\浪潮\\本部工作\\曹成峰\\身份证\\gray.jpg"));

        String str = RuntimeUtil.execForStr("ipconfig -all");
        System.out.println(str);
    }
}
