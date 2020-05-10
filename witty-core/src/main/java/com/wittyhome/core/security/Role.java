package com.wittyhome.core.security;

public enum Role 
{
	USER 		("ROLE_USER"),
	ADMIN 		("ROLE_ADMIN"),
	SUPERADMIN	("ROLE_SUPERADMIN");
	
	private String roleName;
	
	Role(String roleName)
	{
		this.roleName = roleName;
	}
	
	public static Role getRoleByName(String name)
	{
		for (Role role: Role.values()) {
			if (role.roleName.equals(name)) {
				return role;
			}
		}
		
		throw new IllegalArgumentException("Role with name: ".concat(name).concat(" not found"));
	}
	
	public String getRoleName()
	{
		return roleName;
	}
}
