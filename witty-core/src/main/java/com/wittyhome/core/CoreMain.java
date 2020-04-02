package com.wittyhome.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.wittyhome"})
@SpringBootApplication(scanBasePackages = {"com.wittyhome"})
public class CoreMain 
{
	private static Logger LOG = LoggerFactory.getLogger(CoreMain.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(CoreMain.class, args);
		
		LOG.info("WittyServer started successfully");
	}
}
