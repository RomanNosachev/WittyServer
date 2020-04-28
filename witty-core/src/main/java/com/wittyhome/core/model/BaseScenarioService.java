package com.wittyhome.core.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wittyhome.core.task.BaseScenarioRepository;
import com.wittyhome.module_base.model.ScenarioService;
import com.wittyhome.module_base.task.Scenario;

@Service
public class BaseScenarioService 
implements ScenarioService
{
	private static int DEFAULT_PAGE_SIZE = 10;
	
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

	@Override
	public void deleteById(String id) 
	{
		repository.deleteById(id);
	}

	@Override
	public Page<Scenario> findAll(int page) 
	{
		return findAll(page, DEFAULT_PAGE_SIZE);
	}

	@Override
	public Page<Scenario> findAll(int page, int size) 
	{
		Pageable request = PageRequest.of(page, size);
		
		return findAll(request);
	}

	@Override
	public Page<Scenario> findAll(Pageable pageable) 
	{		
		return repository.findAll(pageable);
	}

	@Override
	public List<Scenario> findAll(Sort sort) 
	{
		return repository.findAll(sort);
	}
}