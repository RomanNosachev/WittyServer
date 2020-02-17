package com.witty_home.module_base.view;

import org.springframework.stereotype.Component;

@Component
public class StringView 
implements View<StringResponce> 
{
	@Override
	public void display(StringResponce responce) 
	{
		System.out.println("Responce: " + responce.toString());
	}
}
