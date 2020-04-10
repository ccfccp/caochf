package com.ccf.designpattern._07builder.sample01;

public class SubMealBuilderB extends MealBuilder
{
	public void buildFood()
	{
		meal.setFood("一个鸡肉卷");
	}
	public void buildDrink()
	{
		meal.setDrink("一杯果汁");
	}
}