package com.wittyhome.core.generator;


import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import com.wittyhome.module_base.command.Action;
import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;
import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.model.ScenarioService;
import com.wittyhome.module_base.task.Rule;
import com.wittyhome.module_base.task.Scenario;
import com.wittyhome.module_base.task.Task;
import com.wittyhome.module_base.utils.factory.ActionFactory;
import com.wittyhome.module_base.utils.factory.RequestFactory;

@Controller
public class ScenarioController 
implements Generator<StringRequest>
{
	private static Logger LOG = LoggerFactory.getLogger(ScenarioController.class);
	
	private Dispatcher dispatcher;
	
	private ScenarioService service;
	
	private RequestFactory requestFactory;
	private ActionFactory actionFactory;
	
	@Autowired
	public ScenarioController(Dispatcher dispatcher, ScenarioService service,
			RequestFactory requestFactory, ActionFactory actionFactory) {
		this.dispatcher = dispatcher;
		this.service = service;
		
		this.requestFactory = requestFactory;
		this.actionFactory = actionFactory;
	}
		
	@GetMapping({"/", "/index"})
	public String index(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			Model model) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		
		model.addAttribute("requestClasses", requestFactory.getAllRequestClasses());
		model.addAttribute("actionClasses", actionFactory.getAllActionClasses());
		model.addAttribute("rule", new Rule());
		
		var requestPrototypes = requestFactory.getAllRequests();
		
		/*
		 * Each request has a parameter name the same its simple class name
		 */
		requestPrototypes.forEach(inputRequest -> {
			model.addAttribute(inputRequest.getClass().getSimpleName(), inputRequest);
		});
		
		var actionPrototypes = actionFactory.getAllActions();
		
		/*
		 * Each action has a parameter name the same its simple class name
		 */
		actionPrototypes.forEach(inputAction -> {
			model.addAttribute(inputAction.getClass().getSimpleName(), inputAction);
		});
		
		Page<Scenario> resultPage = service.findAll(currentPage - 1, pageSize);
				
		model.addAttribute("scenarios", resultPage);
		model.addAttribute("pageNumber", resultPage.getTotalPages());
		
		return "index";
	}
	
	@GetMapping("/editScenario/{id}")
	public String editScenario(@PathVariable("id") String id, Model model)
	{
		Scenario scenario = service.findById(id);
		
		model.addAttribute("scenario", scenario);
		
		return "editScenario";
	}
	
	@PostMapping("/editScenario/{id}")
	public String editScenario(@PathVariable(value = "id", required = true) String id,
			@RequestParam(name = "requestClassName", required = true) String requestClassName, 
			@RequestParam(name = "actionClassName", required = true) String actionClassName,
			@ModelAttribute("scenario") Scenario scenario, 
			WebRequest webRequest,
			Model model) {
						
		Request request = parseRequest(webRequest, requestClassName);
		Action action = parseAction(webRequest, actionClassName);
		
		Task task = new Task(request, action);
		
		scenario.setTask(task);
		
		service.save(scenario);
		
		return "redirect:/editScenario/".concat(id);
	}
	
	@GetMapping("/deleteScenario/{id}")
	public String deleteScenario(@PathVariable(value = "id", required = true) String id, Model model)
	{
		service.deleteById(id);
		
		return "redirect:/index";
	}
	
	@PostMapping("/saveScenario")
	public String saveScenario(@RequestParam(name = "requestClassName", required = true) String requestClassName, 
			@RequestParam(name = "actionClassName", required = true) String actionClassName,
			@ModelAttribute("rule") Rule rule, 
			WebRequest webRequest, Model model) {
		
		Request request = parseRequest(webRequest, requestClassName);
		Action action = parseAction(webRequest, actionClassName);
		
		Task task = new Task(request, action);		
		Scenario scenario = new Scenario(task, rule);
		
		service.save(scenario);
		
		return "redirect:/index";
	}

	@Override
	public void generate(StringRequest request) 
	{
		dispatcher.dispatch(request);		
	}
	
	private Request parseRequest(WebRequest webRequest, String requestClassName)
	{
		Request request = requestFactory.getRequest(requestClassName);
		
		if (Objects.nonNull(request))
		{
			WebRequestDataBinder binder = new WebRequestDataBinder(request, request.getClass().getSimpleName());
		
			/*
			 * From the form each request field is returned as: SimpleClassName.field
			 */
			
			String requestSimpleClassName = request.getClass().getSimpleName();	
			binder.setFieldDefaultPrefix(requestSimpleClassName.concat("."));			
				
			binder.bind(webRequest);
			
			LOG.info("Request ({}) with class {} succesfully binded", request, requestClassName);
		}
		else {
			LOG.error("Request with class name {} is not found", requestClassName);		
		}
		
		return request;
	}
	
	private Action parseAction(WebRequest webRequest, String actionClassName)
	{
		Action action = actionFactory.getAction(actionClassName);
		
		if (Objects.nonNull(action))
		{
			WebRequestDataBinder binder = new WebRequestDataBinder(action, action.getClass().getSimpleName());
			
			/*
			 * From the form each action field is returned as: SimpleClassName.field
			 */
			
			String actionSimpleClassName = action.getClass().getSimpleName();
			binder.setFieldDefaultPrefix(actionSimpleClassName.concat("."));
			
			binder.bind(webRequest);
						
			LOG.info("Action ({}) with class {} succesfully binded", action, actionClassName);
		}
		else {
			LOG.error("Action with class name {} is not found", actionClassName);
		}
		
		return action;
	}
}
