package com.ccf.designpattern._09singleton.sample;

public class Singleton
{
	private static Singleton instance = null;
	
	private Singleton()
	{	
	}
	
	public static Singleton getInstance()
	{
		if(instance==null)
		    instance=new Singleton();	
		return instance;
	}
}