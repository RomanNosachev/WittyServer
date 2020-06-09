package com.wittyhome.module_base.task;

import java.util.Optional;

import org.springframework.data.domain.ExampleMatcher.PropertyValueTransformer;
import org.springframework.stereotype.Component;

@Component
public class AsteriskValueTransformer 
implements PropertyValueTransformer
{
	@Override
	public Optional<Object> apply(Optional<Object> t) {
		if (t.isPresent()) {
			t = Optional.of("*");					
		}
			
		return t;
	}
}
