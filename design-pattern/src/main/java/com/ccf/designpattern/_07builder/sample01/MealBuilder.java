package com.ccf.designpattern._07builder.sample01;

public abstract class MealBuilder
{
	protected Meal meal=new Meal();
	public abstract void buildFood();
	public abstract void buildDrink();
	public Meal getMeal()
	{
		return meal;
	}
}