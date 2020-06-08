package com.wittyhome.core.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.ScenarioRepository; 

@Repository
public interface BaseScenarioRepository 
extends ScenarioRepository, MongoRepository<Scenario, String>
{
	@Query(value = "{ 'task.request._class' : ?0 }")
	Page<Scenario> findByRequestClass(String requestClassName, Pageable pageable);	
	
	@Query(value = "{ 'task.action._class' : ?0 }")
	Page<Scenario> findByActionClass(String actionClassName, Pageable pageable);	
	
	Page<Scenario> findByEnabledTrue(Pageable pageable);
}
