package com.wittyhome.core.task;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.RuleEngine;
import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.Task;
import com.wittyhome.module_base.task.Transformer;

@Component
public class BaseTransformer 
implements Transformer
{
	private static Logger LOG = LoggerFactory.getLogger(BaseTransformer.class);
	
	private BaseScenarioRepository repository;
	
	private RuleEngine engine;
	
	@Autowired
	public BaseTransformer(BaseScenarioRepository repository, RuleEngine engine) 
	{
		this.repository = repository;
		this.engine = engine;
	}
	
	@Override
	public List<Action> findActionsByRequest(Request request) 
	{
		Task exampleTask = new Task(request);
		Scenario exampleScenario = new Scenario(exampleTask);
		
		Example<Scenario> example = Example.of(exampleScenario);
				
		List<Scenario> scenarios = (List<Scenario>) repository.findAll(example);

		List<Action> actions = scenarios.stream()
				.filter(scenario -> scenario.isEnabled())
				.filter(scenario -> engine.evalRule(scenario))
				.map(scenario -> scenario.getTask().getAction())
				.collect(Collectors.toList());
		
		LOG.info("Found {} actions on request: {}", actions.size(), request);
		
		return actions;
	}
}
