package com.wittyhome.module_base.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScenarioRepository
{
	List<Scenario> findByGroup(String group);
	List<String> findAllGroup();
	List<GroupDetails> groupFrequency();
	
	void addGroupById(String id, String group);
	
	void setEnabledGroup(String group, boolean enabled);
	void deleteGroup(String group);
	void leaveGroup(String id, String group);
	
	void setEnabledById(String id, boolean enabled);
	
	Page<Scenario> findByEnabledFalse(Pageable pageable);
	
	Page<Scenario> findAllWithScript(Pageable pageable);
}
