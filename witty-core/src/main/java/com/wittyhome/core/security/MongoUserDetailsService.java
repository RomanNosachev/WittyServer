package com.wittyhome.core.security;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MongoUserDetailsService 
implements UserService
{
	private BaseUserRepository repository;
	
	private MongoTemplate template;
	
	@Autowired
	public MongoUserDetailsService(BaseUserRepository repository, MongoTemplate template) 
	{
		this.repository = repository;
		this.template = template;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserModel user = repository.findByUsername(username);
		
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());
				
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public UserModel save(UserModel user) throws IllegalArgumentException
	{
		return repository.save(user);
	}

	@Override
	public List<UserModel> findAll() 
	{
		return (List<UserModel>) repository.findAll();
	}

	@Override
	public void delete(UserModel user) 
	{
		repository.delete(user);
	}

	@Override
	public void deleteById(String id) 
	{
		repository.deleteById(id);
	}

	@Override
	public UserModel update(UserModel user) 
	{
		return repository.save(user);
	}

	@Override
	public void updateRoles(UserModel user) 
	{
		Query query = Query.query(Criteria.where("id").is(user.getId()));
		Update update = Update.update("roles", user.getRoles());
		
		template.updateFirst(query, update, UserModel.class);
	}
}
