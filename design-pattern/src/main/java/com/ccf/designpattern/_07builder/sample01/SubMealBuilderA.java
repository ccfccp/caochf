package com.ccf.designpattern._07builder.sample01;

public class SubMealBuilderA extends MealBuilder
{
	public void buildFood()
	{
		meal.setFood("һ�����ȱ�");
	}
	public void buildDrink()
	{
	    meal.setDrink("һ������");
	}
}