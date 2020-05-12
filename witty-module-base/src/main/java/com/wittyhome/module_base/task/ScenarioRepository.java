package com.wittyhome.module_base.task;

import java.util.List;

public interface ScenarioRepository
{
	List<Scenario> findByGroup(String group);
	List<String> findAllGroup();
	List<GroupDetails> groupFrequency();
	
	public void addGroupById(String id, String group);
	
	public void setEnabledGroup(String group, boolean enabled);
	public void deleteGroup(String group);
	public void leaveGroup(String id, String group);
	
	void setEnabledById(String id, boolean enabled);
}
