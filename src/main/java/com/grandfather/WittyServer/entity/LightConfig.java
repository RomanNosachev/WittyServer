package com.grandfather.WittyServer.entity;

public class LightConfig 
{
	private String colorString;
	private String effectString;
	
	public LightConfig(String colorString, String effectString) 
	{
		super();
		this.colorString = colorString;
		this.effectString = effectString;
	}

	public String getColorString() 
	{
		return colorString;
	}

	public void setColorString(String colorString) 
	{
		this.colorString = colorString;
	}

	public String getEffectString() 
	{
		return effectString;
	}

	public void setEffectString(String effectString) 
	{
		this.effectString = effectString;
	}
}
