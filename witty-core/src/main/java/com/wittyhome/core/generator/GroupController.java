package com.wittyhome.core.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wittyhome.module_base.model.ScenarioService;

@Controller
public class GroupController 
{
	private ScenarioService service;
	
	@Autowired
	public GroupController(ScenarioService service) 
	{
		this.service = service;
	}

	@GetMapping("/group")
	public String displayGroup(Model model)
	{
		model.addAttribute("groups", service.groupFrequency());
		
		return "/group";	
	}
	
	@GetMapping("/group/{id}")
	public String displayGroup(@PathVariable("id") String group, Model model)
	{
		model.addAttribute("group", group);
		model.addAttribute("scenarios", service.findAllByGroup(group));
		
		return "/viewGroup";
	}
	
	@PostMapping("/disableGroup")
	public String disableGroup(@ModelAttribute("group") String group, Model model)
	{
		service.disableGroup(group);
		
		return "redirect:/group/".concat(group);
	}
	
	@PostMapping("/enableGroup")
	public String enableGroup(@ModelAttribute("group") String group, Model model)
	{
		service.enableGroup(group);
		
		return "redirect:/group/".concat(group);
	}
	
	@PostMapping("/deleteGroup")
	public String deleteGroup(@ModelAttribute("group") String group, Model model)
	{
		service.deleteGroup(group);
		
		return "redirect:/group/";
	}
	
	@PostMapping("/leaveGroup")
	public String leaveGroup(@ModelAttribute("id") String id,
			@ModelAttribute("group") String group,
			Model model) {
		
		service.leaveGroup(id, group);
		
		return "redirect:/group/".concat(group);
	}
}
