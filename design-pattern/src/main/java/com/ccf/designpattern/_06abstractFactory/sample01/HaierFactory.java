package com.ccf.designpattern._06abstractFactory.sample01;

public class HaierFactory implements EFactory
{
	public Television produceTelevision()
	{
		return new HaierTelevision();
	}
	
	public AirConditioner produceAirConditioner()
	{
		return new HairAirConditioner();
	}
}