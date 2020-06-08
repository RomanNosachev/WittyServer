package com.wittyhome.module_base.task;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class ScenarioRepositoryImpl 
implements ScenarioRepository
{
	@Autowired
	private MongoTemplate template;
	
	@Override
	public List<Scenario> findByGroup(String group) 
	{
		Query query = new Query(Criteria.where("groups").all(group));
		
		return template.find(query, Scenario.class);
	}

	@Override
	public void setEnabledById(String id, boolean enabled) 
	{
		Query query = new Query(Criteria.where("id").is(id));
		Update update = Update.update("enabled", enabled);
		
		template.updateFirst(query, update, Scenario.class);
	}

	@Override
	public List<String> findAllGroup() 
	{		
		return template.findDistinct("groups", Scenario.class, String.class);
	}

	@Override
	public List<GroupDetails> groupFrequency() 
	{						
		Aggregation pipeline = newAggregation(
				unwind("groups"),
				group("groups").count().as("total"));
		
		return template.aggregate(pipeline, Scenario.class, GroupDetails.class).getMappedResults();
	}

	@Override
	public void setEnabledGroup(String group, boolean enabled) 
	{
		Query query = new Query(Criteria.where("groups").all(group)); 
		Update update = Update.update("enabled", enabled);
		
		template.updateMulti(query, update, Scenario.class);
	}

	@Override
	public void deleteGroup(String group) 
	{
		Query query = new Query(Criteria.where("groups").all(group)); 
		Update update = new Update().pull("groups", group);

		template.updateMulti(query, update, Scenario.class);
	}

	@Override
	public void leaveGroup(String id, String group) 
	{
		Query query = new Query(Criteria.where("id").is(id)); 
		Update update = new Update().pull("groups", group);
		
		template.updateFirst(query, update, Scenario.class);
	}

	@Override
	public void addGroupById(String id, String group) 
	{
		Query query = new Query(Criteria.where("id").is(id)); 
		Update update = new Update().addToSet("groups", group);

		template.updateFirst(query, update, Scenario.class);
	}

	@Override
	public Page<Scenario> findByEnabledFalse(Pageable pageable) 
	{
		Query query = new Query(Criteria.where("enabled").ne(Boolean.TRUE))
				.with(pageable);
		
		List<Scenario> list = template.find(query, Scenario.class);
		
		return PageableExecutionUtils.getPage(list, pageable, 
				() -> template.count(Query.of(query).limit(-1).skip(-1), Scenario.class));
	}

	@Override
	public Page<Scenario> findAllWithScript(Pageable pageable) 
	{
		Query query = new Query(Criteria.where("rule.script").exists(true).ne(""))
				.with(pageable);
		
		List<Scenario> list = template.find(query, Scenario.class);
		
		return PageableExecutionUtils.getPage(list, pageable, 
				() -> template.count(Query.of(query).limit(-1).skip(-1), Scenario.class));
	}
}
