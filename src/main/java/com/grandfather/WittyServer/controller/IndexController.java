package com.grandfather.WittyServer.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grandfather.WittyServer.broker.BrokerLauncher;

@Controller
public class IndexController 
{
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showIndexPage(Model model, HttpSession session)
	{
		try {
			new BrokerLauncher().start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
}
