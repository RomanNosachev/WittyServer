package com.wittyhome.module_base.dispatcher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.wittyhome.module_base.generator.Request;

@NoRepositoryBean
public interface RequestRepository 
extends CrudRepository<Request, String>,
PagingAndSortingRepository<Request, String>,
QueryByExampleExecutor<Request>
{

}
