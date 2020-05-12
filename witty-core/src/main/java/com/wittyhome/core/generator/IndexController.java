package com.wittyhome.core.generator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wittyhome.module_base.attribute.GeneralAttributeRegistry;
import com.wittyhome.module_base.dispatcher.RequestRepository;

@Controller
public class IndexController 
{
	private GeneralAttributeRegistry attributeRegistry;
	private RequestRepository requestRepository;
	
	@Autowired
	public IndexController(RequestRepository repository, GeneralAttributeRegistry attributeRegistry) 
	{
		this.requestRepository = repository;
		this.attributeRegistry = attributeRegistry;
	}

	@GetMapping({"/", "/index"})
	public String displayIndexPage(Model model, HttpSession session)
	{		
		model.addAttribute("requests", requestRepository.findAll(PageRequest.of(0, 9)));
		model.addAttribute("attributes", attributeRegistry.findAll());
		
		return "index";
	}
}
