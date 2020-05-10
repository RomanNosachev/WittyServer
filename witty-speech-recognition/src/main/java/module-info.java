module com.wittyhome.speech_recognition {
	exports com.wittyhome.speech_recognition.generator;
	
	opens com.wittyhome.speech_recognition.generator to spring.core;
	
	requires transitive com.wittyhome.module_base;
	requires spring.context;
	requires spring.beans;
	requires java.desktop;
	requires org.slf4j;
	requires spring.websocket;
	requires java.annotation;
	requires com.google.gson;
	requires spring.web;
	requires spring.data.commons;
	requires java.validation;
}