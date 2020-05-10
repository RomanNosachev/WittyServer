package com.wittyhome.core.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.wittyhome.core.security.RoleReadingConverter;
import com.wittyhome.core.security.RoleWritingConverter;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration 
{
    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        
        converters.add(new RoleWritingConverter());
        converters.add(new RoleReadingConverter());
        
        return new MongoCustomConversions(converters);
    }
}
