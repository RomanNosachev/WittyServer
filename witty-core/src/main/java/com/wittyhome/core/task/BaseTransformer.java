package com.wittyhome.core.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.CustomExampleSupplier;
import com.wittyhome.module_base.task.RuleEngine;
import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.Task;
import com.wittyhome.module_base.task.Transformer;

@Component
public class BaseTransformer 
implements Transformer
{
	private static Logger LOG = LoggerFactory.getLogger(BaseTransformer.class);
	
	private Map<Class<? extends Request>, CustomExampleSupplier<? extends Request>> exampleSupplierRegistry;
	
	private BaseScenarioRepository repository;
	
	private RuleEngine engine;
	
	@Autowired
	public BaseTransformer(BaseScenarioRepository repository, RuleEngine engine) 
	{
		this.repository = repository;
		this.engine = engine;
	}
	
	@Autowired
	public void setExampleSupplierRegistry(List<CustomExampleSupplier<? extends Request>> suppliers)
	{
		exampleSupplierRegistry = new HashMap<>(suppliers.size());
		
		suppliers.forEach(supplier -> {
			exampleSupplierRegistry.put(supplier.getExampleRequestClass(), supplier);
		});
	}
	
	@Override
	public List<Action> findActionsByRequest(Request request) 
	{
		final Task exampleTask = new Task(request);
		final Scenario exampleScenario = new Scenario(exampleTask);
		exampleScenario.setEnabled(true);
		
		Example<Scenario> example = Example.of(exampleScenario);
				
		List<Scenario> scenarios = repository.findAll(example);

		/*
		 * Query by example (QBE), provided by CustomExampleSupplier
		 */
		if (Objects.nonNull(exampleSupplierRegistry) && !exampleSupplierRegistry.isEmpty())
		{
			CustomExampleSupplier<? extends Request> customExampleSupplier = exampleSupplierRegistry.get(request.getClass());
			
			if (Objects.nonNull(customExampleSupplier)) 
			{
				Example<Scenario> customExample = customExampleSupplier.get(exampleScenario);

				if (Objects.nonNull(customExample)) 
				{
					List<Scenario> customScenarios = repository.findAll(customExample);

					Set<Scenario> mergeSet = new LinkedHashSet<>(scenarios);
					mergeSet.addAll(customScenarios);
					
					scenarios = new ArrayList<>(mergeSet);
				}
			}
			
		}
		
		List<Action> actions = scenarios.stream()
				.peek(scenario -> scenario.getTask().setRequest(request))
				.filter(scenario -> engine.evalRule(scenario))
				.map(scenario -> scenario.getTask().getAction())
				.collect(Collectors.toList());
		
		LOG.info("Found {} actions on request: {}", actions.size(), request);
		
		return actions;
	}
}
