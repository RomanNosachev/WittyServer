package com.wittyhome.core.dispatcher;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestRepositoryConfiguration 
{
	private MongoTemplate mongoTemplate;
	
	@Autowired
	public RequestRepositoryConfiguration(MongoTemplate mongoTemplate)
	{
		this.mongoTemplate = mongoTemplate;
	}
	
	public String getRequestCollectionName()
	{
		return "request";
	}
	
	@PostConstruct
	public void createRequestRepisitory()
	{
		if (!mongoTemplate.getCollectionNames().contains(getRequestCollectionName())) 
		{
			CollectionOptions options = CollectionOptions.empty()
	    			.capped()
	    			.size(5242880)
	    			.maxDocuments(5000);
	    	
	    	mongoTemplate.createCollection(getRequestCollectionName(), options);   
		}
	}
}
