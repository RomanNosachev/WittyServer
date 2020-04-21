package com.wittyhome.core.generator;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.task.ActionRepository;
import com.wittyhome.module_base.utils.factory.ActionFactory;

@Controller
public class ActionController 
{
	private static Logger LOG = LoggerFactory.getLogger(ActionController.class);
	
	private ActionRepository repository;
	
	private ActionFactory factory;
	
	@Autowired
	public ActionController(ActionRepository repository, ActionFactory factory) 
	{
		this.repository = repository;
		this.factory =  factory;
	}

	@GetMapping("/action")
	public String displayAction(Model model)
	{
		model.addAttribute("actionClasses", factory.getAllActionClasses());
		
		var actionPrototypes = factory.getAllActions();
		
		actionPrototypes.forEach(inputAction -> {
			model.addAttribute(inputAction.getClass().getSimpleName(), inputAction);
		});
		
		model.addAttribute("actions", repository.findAll());
		
		return "action";
	}
	
	@PostMapping("/saveAction")
	public String saveAction(@RequestParam(name = "actionClassName") String actionClassName,
	WebRequest webRequest, Model model) 
	{
		Action action = factory.getAction(actionClassName);
		
		if (Objects.nonNull(action))
		{
			WebRequestDataBinder binder = new WebRequestDataBinder(action);
			binder.bind(webRequest);
			
			BindingResult result = binder.getBindingResult();
			
			action = (Action) result.getTarget();
			
			repository.save(action);
			
			LOG.info("Action ({}) with class {} succesfully saved", action, actionClassName);
		}
		else {
			LOG.error("Action with class name {} is not found", actionClassName);
		}

		return "redirect:/action";
	}
}
