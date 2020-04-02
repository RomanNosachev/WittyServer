module com.wittyhome.core {
	exports com.wittyhome.core;
	exports com.wittyhome.core.command;
	exports com.wittyhome.core.generator;
	exports com.wittyhome.core.model;
	
	requires transitive com.wittyhome.module_base;

	opens com.wittyhome.core				to spring.core;
	opens com.wittyhome.core.generator		to spring.core;
	opens com.wittyhome.core.command 		to spring.core;
	opens com.wittyhome.core.model 			to spring.core;
	
	requires java.base;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.boot.autoconfigure;
	requires spring.boot;
	requires spring.web;
	requires spring.webmvc;
	
	requires spring.data.mongodb;
	requires org.slf4j;
	requires thymeleaf;
	requires java.desktop;
}