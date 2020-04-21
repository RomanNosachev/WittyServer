module com.wittyhome.module_base {
	exports com.wittyhome.module_base;
	exports com.wittyhome.module_base.model;
	exports com.wittyhome.module_base.command;
	exports com.wittyhome.module_base.generator;
	exports com.wittyhome.module_base.task;
	exports com.wittyhome.module_base.dispatcher;
		
	opens com.wittyhome.module_base.model				to spring.core;
	opens com.wittyhome.module_base.command				to spring.core;
	opens com.wittyhome.module_base.task				to spring.core;
	opens com.wittyhome.module_base.dispatcher			to spring.core;
	opens com.wittyhome.module_base						to spring.core;
	
	requires java.base;
	requires spring.context;
	requires spring.core;
	requires spring.beans;
	requires spring.data.commons;
}