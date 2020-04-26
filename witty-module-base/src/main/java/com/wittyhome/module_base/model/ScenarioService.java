package com.wittyhome.module_base.model;

import java.util.List;

import com.wittyhome.module_base.task.Scenario;

public interface ScenarioService 
{
	public Scenario save(Scenario scenario);
	public List<Scenario> saveAll(Iterable<Scenario> scenarios);
	
	public Scenario findById(String id);
	public List<Scenario> findAll();
	
	public void delete(Scenario scenario);
	public void deleteAll(Iterable<Scenario> scenarios);
}
