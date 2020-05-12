package com.wittyhome.broker.attribute;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.wittyhome.broker.generator.BrokerRequest;
import com.wittyhome.module_base.attribute.AttributeSupplier;
import com.wittyhome.module_base.generator.Request; 

@Component
public class ColorAttributeSupplier 
implements AttributeSupplier<String>
{
	private MongoTemplate template;
	
	@Autowired
	public ColorAttributeSupplier(MongoTemplate template) 
	{
		this.template = template;
	}

	@Override
	public String get() 
	{
		BrokerRequest request = new BrokerRequest();
		request.setTopic("light/color");
		
		Example<BrokerRequest> example = Example.of(request);
		
		Query query = new Query(Criteria.byExample(example));
		
		query.limit(1);
		query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
		
		String result;
		
		try {
			BrokerRequest requestResult = (BrokerRequest) template.find(query, Request.class).get(0);
			
			result = requestResult.getPayload();
		}
		catch (Exception e) {
			return "#000000";
		}
		
		if (Objects.isNull(result) || result.isBlank()) {
			return "#000000";
		}
		
		return result;
	}

	@Override
	public String getAttributeName() 
	{
		return "lightColor";
	}
}
