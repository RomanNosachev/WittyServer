package com.grandfather.WittyServer.utils;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.grandfather.WittyServer.WittyServer;

public class ServletInitializer 
extends SpringBootServletInitializer 
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(WittyServer.class);
	}
}