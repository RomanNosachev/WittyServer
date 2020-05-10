package com.wittyhome.broker.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MqttTopicValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MqttTopicConstraint 
{
	String message() default "Invalid MQTT topic";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
