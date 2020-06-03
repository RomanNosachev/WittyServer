package com.wittyhome.core.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService 
extends UserDetailsService
{
	public UserModel save(UserModel user);
	
	public List<UserModel> findAll();
	
	public UserModel update(UserModel user);
	public void updateRoles(UserModel user);
	
	public void delete(UserModel user);
	public void deleteById(String id);
}
