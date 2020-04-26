package com.wittyhome.core.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittyhome.core.task.BaseScenarioRepository;
import com.wittyhome.module_base.model.ScenarioService;
import com.wittyhome.module_base.task.Scenario;

@Service
public class BaseScenarioService 
implements ScenarioService
{
	private BaseScenarioRepository repository;
	
	@Autowired
	public BaseScenarioService(BaseScenarioRepository repository) 
	{
		this.repository = repository;	
	}

	@Override
	public Scenario save(Scenario scenario)
	{
		return repository.save(scenario);
	}
	
	@Override
	public List<Scenario> saveAll(Iterable<Scenario> scenarios)
	{
		return repository.saveAll(scenarios);
	}
	
	@Override
	public Scenario findById(String id)
	{
		return repository.findById(id).get();
	}
	
	@Override
	public List<Scenario> findAll()
	{		
		return repository.findAll();
	}
	
	@Override
	public void delete(Scenario scenario)
	{
		repository.delete(scenario);
	}
	
	@Override
	public void deleteAll(Iterable<Scenario> scenarios) 
	{
		repository.deleteAll(scenarios);
	}
}