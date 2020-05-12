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
import com.wittyhome.module_base.task.GroupDetails;
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

	@Override
	public List<Scenario> findAllByGroup(String group) 
	{
		return repository.findByGroup(group);
	}

	@Override
	public Page<Scenario> findAllEnabled(int page) 
	{		
		return findAllEnabled(page, DEFAULT_PAGE_SIZE);
	}

	@Override
	public Page<Scenario> findAllEnabled(int page, int size) 
	{
		Pageable pageable = PageRequest.of(page, size);
		
		return findAllEnabled(pageable);
	}

	@Override
	public Page<Scenario> findAllEnabled(Pageable pageable) 
	{
		return repository.findByEnabledTrue(pageable);
	}

	@Override
	public void enableById(String id) 
	{
		repository.setEnabledById(id, true);
	}

	@Override
	public void disableById(String id) 
	{
		repository.setEnabledById(id, false);
	}

	@Override
	public List<String> findAllGroup() 
	{
		return repository.findAllGroup();
	}

	@Override
	public List<GroupDetails> groupFrequency() 
	{
		return repository.groupFrequency();
	}

	@Override
	public void enableGroup(String group) 
	{
		repository.setEnabledGroup(group, true);
	}

	@Override
	public void disableGroup(String group) 
	{
		repository.setEnabledGroup(group, false);
	}

	@Override
	public void deleteGroup(String group) 
	{
		repository.deleteGroup(group);
	}

	@Override
	public void leaveGroup(String id, String group) 
	{
		repository.leaveGroup(id, group);
	}

	@Override
	public void addGroupById(String id, String group) 
	{
		repository.addGroupById(id, group);
	}
}