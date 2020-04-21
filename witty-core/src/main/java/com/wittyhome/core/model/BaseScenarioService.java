package com.wittyhome.core.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.wittyhome.core.task.BaseActionRepository;
import com.wittyhome.core.task.BaseRequestRepository;
import com.wittyhome.core.task.BaseScenarioRepository;
import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.model.ScenarioService;
import com.wittyhome.module_base.task.Scenario;

@Service
public class BaseScenarioService 
implements ScenarioService
{
	private BaseScenarioRepository repository;
	
	private BaseRequestRepository requestRepository;
	private BaseActionRepository actionRepository;
	
	@Autowired
	public BaseScenarioService(BaseScenarioRepository repository, 
			BaseRequestRepository requestRepository, BaseActionRepository actionRepository) {
		this.repository = repository;
		
		this.requestRepository = requestRepository;
		this.actionRepository = actionRepository;		
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
	public Request saveRequest(Request request) 
	{
		return requestRepository.save(request);
	}

	@Override
	public Request findRequestById(String id) 
	{
		return requestRepository.findById(id).get();
	}

	@Override
	public List<Request> findAllRequestByExample(Request request) 
	{
		Example<Request> example = Example.of(request);
		
		return requestRepository.findAll(example);
	}

	@Override
	public List<Request> findAllRequest() 
	{
		return requestRepository.findAll();
	}

	@Override
	public void deleteRequest(Request request) 
	{
		requestRepository.delete(request);
	}

	@Override
	public void deleteRequestById(String id) 
	{
		requestRepository.deleteById(id);
	}
	
	@Override
	public Action saveAction(Action action) 
	{
		return actionRepository.save(action);
	}

	@Override
	public Action findActionById(String id) 
	{
		return actionRepository.findById(id).get();
	}

	@Override
	public List<Action> findActionByExample(Action action) 
	{
		Example<Action> example = Example.of(action);
		
		return actionRepository.findAll(example);
	}

	@Override
	public List<Action> findAllAction() 
	{
		return actionRepository.findAll();
	}

	@Override
	public void deleteAction(Action action) 
	{
		actionRepository.delete(action);
	}
	
	@Override
	public void deleteActionById(String id) 
	{
		actionRepository.deleteById(id);
	}
}