package com.ccf.designpattern._07builder.sample02;

public class Client {
    public static void main(String[] args){
        Builder builder=new MacbookBulder();
        Director director=new Director(builder);
        director.constract("英特尔主板","Retina显示器","Mac OS X系统");
        System.out.println(builder.getComputer().toString());
    }
}
