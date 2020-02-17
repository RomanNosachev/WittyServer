package com.witty_home.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.witty_home.command_module.model.CommandEntity;
import com.witty_home.command_module.model.CommandRepository;
import com.witty_home.core.WittyManager;
import com.witty_home.module_base.controller.JsonRequest;
import com.witty_home.module_base.controller.Request;
import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.generator.Generator;
import com.witty_home.module_base.model.Action;
import com.witty_home.module_base.model.StringAction;
import com.witty_home.module_base.view.Responce;
import com.witty_home.module_base.view.StringResponce;
import com.witty_home.module_base.view.View;

@Controller
@RequestMapping({"/", "index"})
@Primary
public class CoreController 
implements Generator<StringRequest>,
View<StringResponce>
{
	@Autowired
	private WittyManager coreManager;
	
	@Autowired
	private CommandRepository commandRepository;
	
	public CoreController() 
	{
	}

	@GetMapping
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("index");
		
		mv.getModel().put("data", "welcome");
		
		System.out.println("Request");
		
		StringRequest request = new JsonRequest("JSON");
		request.setRequest("Json request");
		
		generate(request, StringRequest.class);
		
		Request requestEntity = new StringRequest("String request");
		Request requestEntity2 = new JsonRequest("Body");
		
		Action action = new StringAction();
				
		CommandEntity entity = new CommandEntity(requestEntity, action);
		CommandEntity entity2 = new CommandEntity(requestEntity2, action);
		
		commandRepository.deleteAll();
		commandRepository.save(entity);
		commandRepository.save(entity2);
		
		
		
		System.out.println(commandRepository.findByRequest(requestEntity));
		
		commandRepository.findAll().forEach(command -> {
			System.out.println(command.getRequest().getClass().getName());
		});
		
		return mv;
	}

	@Override
	public void generate(StringRequest request, Class<StringRequest> clazz) 
	{
		coreManager.handle(request, clazz);
	}

	@Override
	public void display(StringResponce responce) 
	{
		System.out.println("Core controller:");
		System.out.println(responce.toString());
	}
}
