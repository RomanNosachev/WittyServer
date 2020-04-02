package com.wittyhome.core.model;

import com.wittyhome.module_base.model.Service;

@org.springframework.stereotype.Service
public class StringService 
implements Service
{
	public void print(String string)
	{
		System.out.println(string);
	}
}
