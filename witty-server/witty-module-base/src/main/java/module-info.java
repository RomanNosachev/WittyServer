module com.witty_home.module_base {
	exports com.witty_home.module_base;
	exports com.witty_home.module_base.model;
	exports com.witty_home.module_base.view;
	exports com.witty_home.module_base.controller;
	exports com.witty_home.module_base.command;
	exports com.witty_home.module_base.generator;
	
	exports com.witty_home.module_base.utils.factory.command;
	exports com.witty_home.module_base.utils.builder.command;
	exports com.witty_home.module_base.utils.factory.controller;
	exports com.witty_home.module_base.utils.builder.controller;
		
	opens com.witty_home.module_base.controller 				to spring.core;
	opens com.witty_home.module_base.model						to spring.core;
	opens com.witty_home.module_base.view						to spring.core;
	opens com.witty_home.module_base.utils.builder.command 		to spring.core;
	opens com.witty_home.module_base.utils.builder.controller	to spring.core;
	opens com.witty_home.module_base.utils.factory.command		to spring.core;
	opens com.witty_home.module_base.utils.factory.controller	to spring.core;
	opens com.witty_home.module_base							to spring.core;
	
	requires java.base;
	requires spring.context;
	requires spring.core;
	requires spring.beans;
}