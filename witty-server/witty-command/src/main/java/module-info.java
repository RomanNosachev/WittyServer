module com.witty_home.command_module {
	exports com.witty_home.command_module.model;
	
	opens com.witty_home.command_module.model;
	
	requires transitive com.witty_home.module_base;
	requires spring.data.commons;
	requires spring.data.mongodb;
}