package com.wittyhome.module_base.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.wittyhome.module_base.task.Scenario;

public interface ScenarioService 
{
	public Scenario save(Scenario scenario);
	public List<Scenario> saveAll(Iterable<Scenario> scenarios);
	
	public Scenario findById(String id);
	
	public List<Scenario> findAll();
	public Page<Scenario> findAll(int page);
	public Page<Scenario> findAll(int page, int size);
	public Page<Scenario> findAll(Pageable pageable);
	public List<Scenario> findAll(Sort sort);
	
	public void delete(Scenario scenario);
	public void deleteById(String id);
	public void deleteAll(Iterable<Scenario> scenarios);
}
