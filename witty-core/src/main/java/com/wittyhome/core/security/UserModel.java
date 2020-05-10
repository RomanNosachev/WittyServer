package com.wittyhome.core.security;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wittyhome.module_base.security.User;

@Document
public class UserModel	
implements User
{
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String username;
	
	private String password;
	
	private Set<Role> roles;
	
	public UserModel() {}
	
	public UserModel(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		roles = Set.of(Role.USER);
	}
	
	public UserModel(String id, String username, String password)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		
		roles = Set.of(Role.USER);
	}

	@Override
	public String getId() 
	{
		return id;
	}

	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public void setUsername(String username) 
	{
		this.username = username;
	}

	@Override
	public String getPassword() 
	{
		return password;
	}

	@Override
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Set<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(Set<Role> roles) 
	{
		this.roles = roles;
	}
	
	public void setRoles(Role ... roles)
	{
		this.roles = Set.of(roles);
	}
}

