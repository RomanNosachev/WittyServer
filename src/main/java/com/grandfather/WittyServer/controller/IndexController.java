package com.grandfather.WittyServer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grandfather.WittyServer.entity.LightConfig;

@Controller
@RequestMapping({"/", "index"})
public class IndexController 
{	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showIndexPage(Model model, @ModelAttribute("lightConfig") LightConfig config, HttpSession session)
	{		
		LightConfig createdConfig = (LightConfig) session.getAttribute("newConfig");
		
		if (createdConfig != null)
		{
			model.addAttribute("config", createdConfig);
			session.removeAttribute("newConfig");
		}
		
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String colorRequest(Model model, @ModelAttribute("lightConfig") LightConfig config, HttpSession session)
	{		
		session.setAttribute("newConfig", config);
		
		System.out.println(config.getColorString());
		System.out.println(config.getEffectString());
		
		return "redirect:/";
	}
}
