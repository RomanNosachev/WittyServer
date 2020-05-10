package com.wittyhome.core.task;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.attribute.GeneralAttributeRegistry;
import com.wittyhome.module_base.task.Rule;
import com.wittyhome.module_base.task.RuleEngine;
import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.Task;

@Component
public class GroovyRuleEngine 
implements RuleEngine
{
	private static Logger LOG = LoggerFactory.getLogger(GroovyRuleEngine.class);
	
	private GeneralAttributeRegistry attributeRegistry;
	
	private ScriptEngine engine;

	@Autowired
	public GroovyRuleEngine(GeneralAttributeRegistry attributeRegistry) 
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		
		this.engine = manager.getEngineByName("Groovy");
		
		this.attributeRegistry = attributeRegistry;
	}
	
	@Override
	public boolean validate(Scenario scenario) 
	{
		Task task = scenario.getTask();
		Rule rule = scenario.getRule();
		
		if (Objects.isNull(task) || Objects.isNull(task.getAction()) || Objects.isNull(task.getRequest())) {
			return false;
		}
		else {
			if (Objects.isNull(rule) || rule.getScript().isBlank()) {
				return true;
			}
		}
		
		engine.put("request", task.getRequest());
		engine.put("action", task.getAction());
		
		Compilable compilable = (Compilable) engine;
		
		try {
			compilable.compile(scenario.getRule().getScript());
		} catch (ScriptException e) {
			LOG.error("Syntax error", e);
			
			return false;
		}
		
		return true;
	}
	
	/*
	 * Insert "importPackage = null" at start script to prohibit the use libraries
	 */
	@Override
	public boolean evalRule(Scenario scenario)
	{
		boolean result = false;
		
		Task task = scenario.getTask();
		Rule rule = scenario.getRule();
		
		if (Objects.isNull(task) || Objects.isNull(task.getAction()) || Objects.isNull(task.getRequest())) {
			return false;
		}
		else {
			if (Objects.isNull(rule) || rule.getScript().isBlank()) {
				return true;
			}
		}
		
		try {
			String script = rule.getScript();
			
			List<String> attributePrefixList = attributeRegistry.getAllPrefix();
			
			for (String prefix: attributePrefixList) 
			{
				int prefixIndex = script.indexOf(prefix);
				
				if (prefixIndex != -1) 
				{
					int dotIndex = script.indexOf(".", prefixIndex);
					
					Pattern pattern = Pattern.compile("[a-zA-Z_]+[a-zA-Z_0-9]( |\\;|\\(|\\)|\\-|\\+|\\=|\\/|\\*){1,1}");
					Matcher matcher = pattern.matcher(script.substring(dotIndex + 1));
					
					if (matcher.find())
					{
						String match = matcher.group();			
						String attrName = match.substring(0, match.length() - 1);
												
						LOG.info("Found attribute {}.{}", prefix, attrName);
						
						script = script.replaceAll(prefix.concat("."), "");
						
						engine.put(attrName, attributeRegistry.findByName(attrName));
					}
				}
			}
						
			engine.put("request", task.getRequest());
			engine.put("action", task.getAction());
			
			result = (boolean) engine.eval(script);
		} catch (ScriptException e) {
			LOG.error("Invalid syntax", e);
		} catch (NullPointerException e) {
			LOG.error("Boolean result not returned", e);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		}
		
		return result;
	}
}
