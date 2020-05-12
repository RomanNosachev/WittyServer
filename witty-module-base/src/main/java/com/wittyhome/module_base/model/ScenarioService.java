package com.wittyhome.module_base.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.wittyhome.module_base.task.GroupDetails;
import com.wittyhome.module_base.task.Scenario;

public interface ScenarioService 
{
	public Scenario save(Scenario scenario);
	public List<Scenario> saveAll(Iterable<Scenario> scenarios);
	
	public Scenario findById(String id);
	
	public List<Scenario> findAll();
	public List<Scenario> findAll(Sort sort);
	
	public Page<Scenario> findAll(int page);
	public Page<Scenario> findAll(int page, int size);
	public Page<Scenario> findAll(Pageable pageable);
	
	public Page<Scenario> findAllEnabled(int page);
	public Page<Scenario> findAllEnabled(int page, int size);
	public Page<Scenario> findAllEnabled(Pageable pageable);
	
	public List<Scenario> findAllByGroup(String group);
	
	public List<String> findAllGroup();
	public List<GroupDetails> groupFrequency();
	
	public void addGroupById(String id, String group);
	
	public void enableById(String id);
	public void disableById(String id);
	
	public void enableGroup(String group);
	public void disableGroup(String group);
	public void deleteGroup(String group);
	public void leaveGroup(String id, String group);
	
	public void delete(Scenario scenario);
	public void deleteById(String id);
	public void deleteAll(Iterable<Scenario> scenarios);
}
