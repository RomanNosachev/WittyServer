package com.wittyhome.core.generator;

import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class WebMvcConfiguration 
{
	@Autowired
	SpringTemplateEngine templateEngine;
	
	@PostConstruct
	public void templateEngineConfiguration() 
	{
		templateEngine.setRenderHiddenMarkersBeforeCheckboxes(true);
	}
	
	@Bean
	public Function<List<String>, String> currentUrlWithoutParamList() 
	{		
	    return params -> {	 	    	
	    	var builder = ServletUriComponentsBuilder.fromCurrentRequest();
	    	
	    	params.forEach(param -> {
	    		builder.replaceQueryParam(param);
	    	});

			return builder.toUriString();
	    };
	}
	
	@Bean
	public Function<String, String> currentUrlWithoutParam() 
	{
		return param -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString();
	}
}
