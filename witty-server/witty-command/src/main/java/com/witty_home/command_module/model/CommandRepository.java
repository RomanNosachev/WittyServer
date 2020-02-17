package com.witty_home.command_module.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.witty_home.module_base.controller.Request;

public interface CommandRepository 
extends MongoRepository<CommandEntity, String>
{
	public CommandEntity findByRequest(Request request);
}
