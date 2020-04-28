package com.wittyhome.module_base.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ScenarioRepository
extends CrudRepository<Scenario, String>,
PagingAndSortingRepository<Scenario, String>
{
	
}
