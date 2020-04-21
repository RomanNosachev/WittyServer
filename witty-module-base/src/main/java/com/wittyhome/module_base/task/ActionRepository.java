package com.wittyhome.module_base.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.wittyhome.module_base.command.Action;

@NoRepositoryBean
public interface ActionRepository 
extends CrudRepository<Action, String>
{
	
}
