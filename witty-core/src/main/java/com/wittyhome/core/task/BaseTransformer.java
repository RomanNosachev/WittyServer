package com.wittyhome.core.task;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.Rule;
import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.Task;
import com.wittyhome.module_base.task.Transformer;

@Component
public class BaseTransformer 
implements Transformer
{
	private static Logger LOG = LoggerFactory.getLogger(BaseTransformer.class);
	
	private BaseScenarioRepository repository;
	
	private ScriptEngine engine;
	
	@Autowired
	public BaseTransformer(BaseScenarioRepository repository) 
	{
		this(repository, "Groovy");		
	}
	
	public BaseTransformer(BaseScenarioRepository repository, String scriptEngineName) 
	{
		this.repository = repository;
		
		ScriptEngineManager manager = new ScriptEngineManager();
		this.engine = manager.getEngineByName(scriptEngineName);
	}
	
	private boolean scriptEval(Scenario scenario)
	{
		boolean result = false;
		
		Task task = scenario.getTask();
		Rule rule = scenario.getRule();
		
		if (Objects.isNull(task) || Objects.isNull(task.getAction()) || Objects.isNull(task.getRequest())) 
		{
			return false;
		}
		else 
		{
			if (Objects.isNull(rule) || rule.getScript().isBlank()) {
				return true;
			}
		}
		
		try {
			engine.put("request", task.getRequest());
			engine.put("action", task.getAction());
			
			result = (boolean) engine.eval(rule.getScript());
		} catch (ScriptException e) {
			LOG.error("Invalid syntax", e);
		} catch (NullPointerException e) {
			LOG.error("Boolean result not returned", e);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		}
		
		return result;
	}
	
	@Override
	public List<Action> findActionsByRequest(Request request) 
	{
		Task exampleTask = new Task(request);
		Scenario exampleScenario = new Scenario(exampleTask);
		
		Example<Scenario> example = Example.of(exampleScenario);
				
		List<Scenario> scenarios = repository.findAll(example);
		
		List<Action> actions = scenarios.stream()
				.filter(scenario -> scriptEval(scenario))
				.map(scenario -> scenario.getTask().getAction())
				.collect(Collectors.toList());
		
		return actions;
	}
}
