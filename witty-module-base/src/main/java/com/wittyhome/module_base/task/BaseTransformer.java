package com.wittyhome.module_base.task;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.MatchMode;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.ExampleMatcher.PropertySpecifiers;
import org.springframework.data.domain.ExampleMatcher.PropertyValueTransformer;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.generator.Request;

@Component
public class BaseTransformer 
implements Transformer
{
	private TaskRepository repository;
	
	@Autowired
	public BaseTransformer(TaskRepository repository) 
	{
		this.repository = repository;
	}
	
	@Override
	public List<Action> findActionsByRequest(Request request) 
	{
		Task exampleTask = new Task(request);
		Example<Task> example = Example.of(exampleTask);
		
		var tasks = repository.findAll(example);
		//
		
		Example<Task> example1 = Example.of(exampleTask, 
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING));
		
		var tasks1 = repository.findAll(example1);
		
		
		//
		return tasks.stream()
				.map(task -> task.getAction())
				.collect(Collectors.toList());
	}
}
