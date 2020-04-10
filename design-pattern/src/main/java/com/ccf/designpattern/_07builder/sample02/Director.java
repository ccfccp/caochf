package com.ccf.designpattern._07builder.sample02;

public class Director {

    Builder mBuilder=null;

    public Director(Builder builder){
        this.mBuilder=builder;
    }

    public void constract(String board,String display,String os){
        mBuilder.builderBoard(board);
        mBuilder.builderDisplay(display);
        mBuilder.builderOs(os);
    }
}
