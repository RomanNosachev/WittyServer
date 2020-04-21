package com.wittyhome.core.task;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.task.ActionRepository;

@Repository
public interface BaseActionRepository 
extends ActionRepository, MongoRepository<Action, String>
{
	
}
