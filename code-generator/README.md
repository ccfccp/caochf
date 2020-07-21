# **项目说明** 
- 本项目基于是基于 renren-generator 定制的代码生成器 
<br>

[TOC]


## 不同点：

> 因为本人的公司使用的是 tkmyabtis + swagger 构建 rest api,而 renren-generator 用的是 mybatis-plus,而且不支持 swagger，所以有了本项目


## 效果

![t0.png](https://i.loli.net/2019/06/18/5d084afb612d815208.png)

![01.png](https://i.loli.net/2019/06/10/5cfe1e40618f397373.png)

![02.png](https://i.loli.net/2019/06/10/5cfe1e572c32463427.png)

![03.png](https://i.loli.net/2019/06/10/5cfe1e78bf5d850438.png)

![04.png](https://i.loli.net/2019/06/10/5cfe1e874151830124.png)

![05.png](https://i.loli.net/2019/06/10/5cfe1e9c1164e41183.png)

![06.png](https://i.loli.net/2019/06/10/5cfe1eaf02fca32310.png)

## 原理分析

> 其实代码生成的原理非常简单，就是查询数据库的信息，然后通过模板引擎渲染出来


## 如何定制开发？

- 1 将写好的模板文件放入 template 目录下, 我是放到了 template/rzx 目录下
- 2 修改 GenUtils 类，getTemplates（模板资源加载的方法），getFileName（文件路径的方法，不一定要改）
- 3 关于模板的可用字段可以参考 GenUtils.generatorCode 方法

## 更多

- tkmybatis 用法。https://mapperhelper.github.io/docs/
- renren-generator https://gitee.com/renrenio/renren-generator
- tkmybatis 源码 https://gitee.com/free/Mapper
- Lemur 代码生成器（写的非常灵活） https://gitee.com/lemur/lemur-generation

## 可能存在的坑

- 因为是自动生成的代码，所以拷贝到自己的项目中的时候 要修改一下引用。包括 实体类 和 xml 的引用。

## 代码地址

> https://github.com/junjun888/generator
