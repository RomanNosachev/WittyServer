package com.wittyhome.module_base.task;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupValidator.class)
@Documented
public @interface GroupConstraint 
{
	String message() default "Group list contains duplicates or blank lines";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
