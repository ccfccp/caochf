package com.ccf.designpattern._07builder.sample01;

public class SubMealBuilderA extends MealBuilder
{
	public void buildFood()
	{
		meal.setFood("一个鸡腿堡");
	}
	public void buildDrink()
	{
		meal.setDrink("一杯可乐");
	}
}