module com.wittyhome.broker {
	exports com.wittyhome.broker.model;
	exports com.wittyhome.broker.generator;
	exports com.wittyhome.broker.command;
	exports com.wittyhome.broker.attribute;
	exports com.wittyhome.broker.task;
	
	opens com.wittyhome.broker.generator 		to spring.core;
	opens com.wittyhome.broker.model			to spring.core;
	opens com.wittyhome.broker.command			to spring.core;
	opens com.wittyhome.broker.attribute		to spring.core;
	opens com.wittyhome.broker.task				to spring.core;
	
	requires transitive com.wittyhome.module_base;
	requires transitive moquette.broker;
	
	requires org.slf4j;
	requires io.netty.buffer;
	requires io.netty.codec.mqtt;
	requires io.netty.common;	
	requires spring.context;
	requires spring.beans;
	requires spring.data.commons;
	requires transitive spring.data.mongodb;
}