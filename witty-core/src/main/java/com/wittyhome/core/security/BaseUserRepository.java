package com.wittyhome.core.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository 
extends MongoRepository<UserModel, String>
{
	UserModel findByUsername(String username);
}
