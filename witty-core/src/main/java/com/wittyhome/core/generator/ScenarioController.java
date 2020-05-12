package com.wittyhome.core.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	private SmartValidator validator;
	
	@Autowired
	public ScenarioController(Dispatcher dispatcher, ScenarioService service, SmartValidator validator) 
	{
		this.dispatcher = dispatcher;
		this.service = service;
		this.validator = validator;
	}
		
	@Autowired
	public void setRequestFactory(RequestFactory requestFactory)
	{
		this.requestFactory = requestFactory;
	}
	
	@Autowired
	public void setActionFactory(ActionFactory actionFactory) 
	{
		this.actionFactory = actionFactory;
	}
	
	@Override
	public void generate(StringRequest request) 
	{
		dispatcher.dispatch(request);		
	}
	
	@GetMapping("/scenario")
	public String displayScenario(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			Model model) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		
		Page<Scenario> resultPage = service.findAll(currentPage - 1, pageSize);
				
		model.addAttribute("scenarios", resultPage);
		model.addAttribute("pageNumber", resultPage.getTotalPages());
		
		return "scenario";
	}
	
	@GetMapping("/addScenario")
	public String addScenario(Model model)
	{
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
				
		return "addScenario";
	}
	
	@GetMapping("/editScenario/{id}")
	public String editScenario(@PathVariable("id") String id, Model model)
	{
		Scenario scenario = service.findById(id);
		
		model.addAttribute("scenario", scenario);
		model.addAttribute("groups", service.findAllGroup());
		
		return "editScenario";
	}
	
	@GetMapping("/editScenario/{id}/fail")
	public String editScenarioOnFail(@PathVariable("id") String id, 
			@ModelAttribute("scenario") Scenario scenario,
			Model model) {
		
		if (Objects.isNull(scenario) || Objects.isNull(scenario.getTask())) {
			return "redirect:/editScenario/".concat(id);
		}
		
		return "editScenario";
	}
	
	@PostMapping("/saveScenario")
	public String saveScenario(@RequestParam(name = "requestClassName", required = true) String requestClassName, 
			@RequestParam(name = "actionClassName", required = true) String actionClassName,
			@ModelAttribute("rule") Rule rule, 
			WebRequest webRequest, 
			Model model, 
			BindingResult result) {
		
		Request request = parseRequest(webRequest, requestClassName);		
		Action action = parseAction(webRequest, actionClassName);
		
		Task task = new Task(request, action);		
		Scenario scenario = new Scenario(task, rule);
		
		ObjectError validationError = validateScenario(scenario, result);
		
		if (Objects.nonNull(validationError)) 
		{
			String errorMessage = validationError.getDefaultMessage();
			
			if (validationError instanceof FieldError) {
				LOG.error("Field [{}] validation error: {}", ((FieldError) validationError).getField(), errorMessage);
			}
			else {
				LOG.error("Validation error: {}", errorMessage);
			}
			
			return onAddScenarioValidationError(scenario, errorMessage, model);
		}
						
		Scenario savedScenario = service.save(scenario);
		
		if (Objects.nonNull(savedScenario) && !savedScenario.getId().isBlank()) {
			return "redirect:/editScenario/".concat(scenario.getId());
		}
		
		return "redirect:/scenario";
	}

	@PostMapping("/editScenario")
	public String editScenario(@RequestParam(name = "requestClassName", required = true) String requestClassName, 
			@RequestParam(name = "actionClassName", required = true) String actionClassName,
			@RequestParam(name = "group") String group,
			@ModelAttribute("scenario") Scenario scenario, 
			WebRequest webRequest,
			RedirectAttributes redirectAttributes,
			BindingResult result) {
						
		Request request = parseRequest(webRequest, requestClassName);
		Action action = parseAction(webRequest, actionClassName);
		
		Task task = new Task(request, action);
		
		scenario.setTask(task);
		
		if (Objects.nonNull(group) && !group.isBlank()) 
		{
			List<String> groups = scenario.getGroups();
			
			if (Objects.isNull(groups)) {
				groups = new ArrayList<String>();
			}
			
			groups.add(group);
			scenario.setGroups(groups);
		}
		
		ObjectError validationError = validateScenario(scenario, result);
		
		if (Objects.nonNull(validationError)) 
		{
			String errorMessage = validationError.getDefaultMessage();
			
			if (validationError instanceof FieldError) {
				LOG.error("Field [{}] validation error: {}", ((FieldError) validationError).getField(), errorMessage);
			}
			else {
				LOG.error("Validation error: {}", errorMessage);
			}
			
			return onEditScenarioValidationError(scenario, errorMessage, redirectAttributes);
		}
		
		service.save(scenario);
				
		return "redirect:/editScenario/".concat(scenario.getId());
	}
	
	@PostMapping("/deleteScenario")
	public String deleteScenario(@RequestParam(name = "id", required = true) String id, Model model)
	{
		service.deleteById(id);
		
		return "redirect:/scenario";
	}
	
	@PostMapping("/disableScenario")
	public String disableScenario(@RequestParam(name = "id", required = true) String id, Model model)
	{
		service.disableById(id);
		
		return "redirect:/scenario";
	}
	
	@PostMapping("/enableScenario")
	public String enableScenario(@RequestParam(name = "id", required = true) String id, Model model)
	{
		service.enableById(id);
		
		return "redirect:/scenario";
	}
	
	private String onAddScenarioValidationError(Scenario scenario, String errorMessage, Model model)
	{
		Request request = scenario.getTask().getRequest();
		Action action = scenario.getTask().getAction();
		Rule rule = scenario.getRule();
		
		model.addAttribute("errorMessage", errorMessage);
		
		model.addAttribute("requestClasses", requestFactory.getAllRequestClasses());
		model.addAttribute("actionClasses", actionFactory.getAllActionClasses());
		
		model.addAttribute("rule", rule);
		
		var requestPrototypes = requestFactory.getAllRequests();
		
		/*
		 * Each request has a parameter name the same its simple class name
		 */
		requestPrototypes.forEach(inputRequest -> {
			model.addAttribute(inputRequest.getClass().getSimpleName(), inputRequest);
		});
		
		model.addAttribute("requestClassName", request.getClass().getName());
		
		model.addAttribute(request.getClass().getSimpleName(), request);
		
		var actionPrototypes = actionFactory.getAllActions();
		
		/*
		 * Each action has a parameter name the same its simple class name
		 */
		actionPrototypes.forEach(inputAction -> {
			model.addAttribute(inputAction.getClass().getSimpleName(), inputAction);
		});
		
		model.addAttribute("actionClassName", action.getClass().getName());
		
		model.addAttribute(action.getClass().getSimpleName(), action);
		
		return "addScenario";
	}
	
	private String onEditScenarioValidationError(Scenario scenario, String errorMessage, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		
		redirectAttributes.addFlashAttribute("scenario", scenario);
		
		return "redirect:/editScenario/".concat(scenario.getId()).concat("/fail");
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
	
	private ObjectError validateScenario(Scenario scenario, BindingResult result)
	{
		validator.validate(scenario, result);
		
		if (result.hasErrors()) 
		{
			ObjectError globalError = result.getGlobalError();

			if (Objects.nonNull(globalError)) {
				return globalError;
			}
			
			return result.getFieldError();
		}
		
		return null;
	}
}
