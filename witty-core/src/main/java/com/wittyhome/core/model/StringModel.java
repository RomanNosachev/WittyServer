package com.wittyhome.core.model;

import org.springframework.stereotype.Service;

import com.wittyhome.module_base.model.Model;
import com.wittyhome.module_base.task.Task;
import com.wittyhome.core.command.StringAction;
import com.wittyhome.core.generator.StringRequest;

@Service
public class StringModel 
implements Model<Task>
{
	public Task getTask()
	{
		StringRequest request = new StringRequest("SR");
		StringAction action = new StringAction("SA");
		
		Task task = new Task(request, action);
		
		return task;
	}
}
