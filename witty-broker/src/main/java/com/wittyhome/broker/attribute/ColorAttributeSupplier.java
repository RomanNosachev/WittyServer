package com.wittyhome.broker.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.wittyhome.broker.generator.BrokerRequest;
import com.wittyhome.module_base.attribute.AttributeSupplier;
import com.wittyhome.module_base.dispatcher.RequestRepository; 

@Component
public class ColorAttributeSupplier 
implements AttributeSupplier<String>
{
	private RequestRepository repository;
	
	@Autowired
	public ColorAttributeSupplier(RequestRepository repository) 
	{
		this.repository = repository;
	}

	@Override
	public String get() 
	{
		BrokerRequest request = new BrokerRequest();
		request.setTopic("light/color");
		
		Example<BrokerRequest> example = Example.of(request);
				
		List<BrokerRequest> result = (List<BrokerRequest>) repository.findAll(example, Sort.by(new Order(Direction.ASC, "id")));
		
		if (!result.isEmpty()) {
			return result.iterator().next().getPayload();
		}
		else {
			return "#000000";
		}
	}

	@Override
	public String getAttributeName() 
	{
		return "lightColor";
	}
}
