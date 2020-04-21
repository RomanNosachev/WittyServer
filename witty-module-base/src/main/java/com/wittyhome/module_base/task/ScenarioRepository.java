package com.wittyhome.module_base.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ScenarioRepository
extends CrudRepository<Scenario, String>
{
	
}
