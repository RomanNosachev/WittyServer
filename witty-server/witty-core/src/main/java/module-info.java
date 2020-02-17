open module com.witty_home.core {
	exports com.witty_home.core;
	
	requires transitive com.witty_home.module_base;
	requires com.witty_home.command_module; 

	//requires com.witty_home.speech_recognition;
	
	requires java.base;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.boot.autoconfigure;
	requires spring.boot;
	requires spring.web;
	requires spring.webmvc;
	requires spring.data.mongodb;
}