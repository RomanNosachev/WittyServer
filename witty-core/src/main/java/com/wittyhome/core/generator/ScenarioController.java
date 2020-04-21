package com.wittyhome.core.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;
import com.wittyhome.module_base.model.ScenarioService;
import com.wittyhome.module_base.task.Scenario;

@Controller
public class ScenarioController 
implements Generator<StringRequest>
{
	private static Logger LOG = LoggerFactory.getLogger(ScenarioController.class);
	
	private Dispatcher dispatcher;
	
	private ScenarioService service;
	
	@Autowired
	public ScenarioController(Dispatcher dispatcher, ScenarioService service) 
	{
		this.dispatcher = dispatcher;
		this.service = service;
	}
	
	@GetMapping({"/", "/index"})
	public String index(Model model)
	{		
		model.addAttribute("scenarioForm", new ScenarioForm());
		
		model.addAttribute("requests", service.findAllRequest());
		model.addAttribute("actions", service.findAllAction());
		
		List<Scenario> scenarios = service.findAll();
		
		model.addAttribute("scenarios", scenarios);
		
		return "index";
	}
	
	@PostMapping("/saveScenario")
	public String saveRules(@ModelAttribute ScenarioForm scenarioForm, BindingResult errors, Model model)
	{
		System.out.println(scenarioForm);
		
		return "redirect:/index";
	}

	@Override
	public void generate(StringRequest request) 
	{
		dispatcher.dispatch(request);		
	}
}
