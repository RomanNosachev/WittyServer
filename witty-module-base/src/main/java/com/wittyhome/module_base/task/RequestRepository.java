package com.wittyhome.module_base.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.wittyhome.module_base.generator.Request;

@NoRepositoryBean
public interface RequestRepository 
extends CrudRepository<Request, String>
{
	
}
