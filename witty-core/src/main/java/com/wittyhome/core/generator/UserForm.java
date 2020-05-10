package com.wittyhome.core.generator;

import java.util.Set;

import com.wittyhome.core.security.Role;
import com.wittyhome.core.security.UserModel;

public class UserForm 
{
	private UserModel user;
	
	private String passwordReplay;
	
	public UserForm() 
	{
		setUser(new UserModel());
	}
	
	public UserForm(UserModel user)
	{
		this.setUser(user);
	}

	public UserModel getUser() 
	{
		return user;
	}

	public void setUser(UserModel user) 
	{
		this.user = user;
	}

	public String getPassword()
	{
		return user.getPassword();
	}
	
	public String getUsername()
	{
		return user.getUsername();
	}
	
	public String getPasswordReplay() 
	{
		return passwordReplay;
	}

	public void setPasswordReplay(String passwordReplay) 
	{
		this.passwordReplay = passwordReplay;
	}
	
	public void setPassword(String password)
	{
		this.user.setPassword(password);
	}
	
	public void setUsername(String username)
	{
		this.user.setUsername(username);
	}
	
	public Set<Role> getRoles()
	{
		return this.user.getRoles();
	}
	
	public boolean checkPasswordReplay()
	{
		return user.getPassword().equals(passwordReplay);
	}
}
