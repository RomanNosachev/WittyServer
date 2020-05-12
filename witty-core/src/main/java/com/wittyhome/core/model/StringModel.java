package com.wittyhome.core.model;

import org.springframework.stereotype.Service;

import com.wittyhome.core.command.StringAction;
import com.wittyhome.core.generator.StringRequest;
import com.wittyhome.module_base.task.Task;

@Service
public class StringModel 
{
	public Task getTask()
	{
		StringRequest request = new StringRequest("SR");
		StringAction action = new StringAction("SA");
		
		Task task = new Task(request, action);
		
		return task;
	}
}
