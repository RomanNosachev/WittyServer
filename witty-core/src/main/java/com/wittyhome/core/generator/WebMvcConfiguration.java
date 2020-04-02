package com.wittyhome.core.generator;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration 
implements WebMvcConfigurer
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		 registry.addResourceHandler("/jquery/**") //
         	.addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.4.1/");

		 registry.addResourceHandler("/popper/**") //
		 	.addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/2.0.2/umd/");
		
		 registry.addResourceHandler("/bootstrap/**") //
		 	.addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1-1/");
	}
}
