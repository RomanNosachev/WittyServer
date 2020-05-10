package com.wittyhome.broker.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.moquette.broker.subscriptions.Topic;

public class MqttTopicValidator 
implements ConstraintValidator<MqttTopicConstraint, String>
{	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		Topic topic = Topic.asTopic(value);
		
		return topic.isValid();
	}
}
