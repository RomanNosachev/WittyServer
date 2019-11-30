package com.grandfather.WittyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.grandfather.WittyServer.broker.BrokerLauncher;

@SpringBootApplication
public class WittyServer extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(WittyServer.class, args);
        
        new Thread(new BrokerLauncher()).start();
    }
}
