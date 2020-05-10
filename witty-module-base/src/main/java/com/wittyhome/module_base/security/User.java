package com.wittyhome.module_base.security;

public interface User
{
	public String getId();
	public void setId(String id);

	public String getUsername();
	public void setUsername(String username);

	public String getPassword();
	public void setPassword(String password);
}
