package com.wittyhome.core.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.ScenarioRepository;
import com.wittyhome.module_base.task.Task; 

@Repository
public interface BaseScenarioRepository 
extends ScenarioRepository, MongoRepository<Scenario, String>
{
	@Query(value = "{ 'request._class' : ?0 }")
	List<Task> findByRequestClass(String requestClassName);	
	
	Page<Scenario> findByEnabledTrue(Pageable pageable);
}