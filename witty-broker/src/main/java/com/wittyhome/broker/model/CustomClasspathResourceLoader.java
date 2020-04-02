package com.wittyhome.broker.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.broker.config.ClasspathResourceLoader;

public class CustomClasspathResourceLoader 
extends ClasspathResourceLoader
{
    private static final Logger LOG = LoggerFactory.getLogger(CustomClasspathResourceLoader.class);
	
    public CustomClasspathResourceLoader() 
	{
		super();
	}
    
	public CustomClasspathResourceLoader(String string, ClassLoader classLoader) 
	{
		super(string, classLoader);
	}
	
	public CustomClasspathResourceLoader(String string)
	{
		super(string);
	}

	@Override
	public Reader loadResource(String relativePath) 
	{
		LOG.info("Loading resource. RelativePath = {}.", relativePath);
        InputStream is = this.getClass().getResourceAsStream(relativePath);
        return is != null ? new InputStreamReader(is, StandardCharsets.UTF_8) : null;
	}
}
