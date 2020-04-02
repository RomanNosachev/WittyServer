package com.wittyhome.module_base.task;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository
extends MongoRepository<Task, String>
{
	@Query(value = "{ 'request._class' : ?0 }")
	public List<Task> findByRequestClass(String requestClassName);	
}
