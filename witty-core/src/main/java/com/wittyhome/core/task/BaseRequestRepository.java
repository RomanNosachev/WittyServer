package com.wittyhome.core.task;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.RequestRepository;

@Repository
public interface BaseRequestRepository 
extends RequestRepository, MongoRepository<Request, String>
{
	
}
