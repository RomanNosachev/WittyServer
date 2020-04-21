package com.wittyhome.module_base.model;

import java.util.List;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.Scenario;

public interface ScenarioService 
{
	public Scenario save(Scenario scenario);
	public List<Scenario> saveAll(Iterable<Scenario> scenarios);
	
	public Scenario findById(String id);
	public List<Scenario> findAll();
	
	public void delete(Scenario scenario);
	public void deleteAll(Iterable<Scenario> scenarios);
	
	public Request saveRequest(Request request);
	
	public Request findRequestById(String id);
	public List<Request> findAllRequestByExample(Request request);
	public List<Request> findAllRequest();
	
	public void deleteRequest(Request request);
	public void deleteRequestById(String id);
	
	public Action saveAction(Action action);
	
	public Action findActionById(String id);
	public List<Action> findActionByExample(Action action);
	public List<Action> findAllAction();
	
	public void deleteAction(Action action);
	public void deleteActionById(String id);
}
