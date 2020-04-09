package com.ccf.designpattern._07builder.sample01;

public class KFCWaiter
{
	private MealBuilder mb;
	public void setMealBuilder(MealBuilder mb)
	{
		this.mb=mb;
	}
	public Meal construct()
	{
		mb.buildFood();
		mb.buildDrink();
		return mb.getMeal();
	}
}