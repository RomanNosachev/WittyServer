package com.wittyhome.core.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wittyhome.core.security.Role;
import com.wittyhome.core.security.UserModel;
import com.wittyhome.core.security.UserService;

@Controller
public class UserController 
{
	private UserService userService;
	
	private PasswordEncoder encoder;
	
	@Autowired
	public UserController(UserService userService, PasswordEncoder encoder) 
	{
		this.userService = userService;
		this.encoder = encoder;
	}

	@GetMapping("/registration")
	public String registration(Model model)
	{
		model.addAttribute("userForm", new UserForm());
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(@ModelAttribute("userForm") UserForm userForm, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
		if (!userForm.checkPasswordReplay()) {
			return "registration";
		}
		
		UserModel user = userForm.getUser();
		
		user.setPassword(encoder.encode(userForm.getPassword()));
		user.setRoles(Role.USER);
		
		try {
			userService.save(user);
		}
		catch (IllegalArgumentException e) {
			return "registration";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String displayLoginPage(Model model)
	{
		model.addAttribute("userForm", new UserForm());
		
		return "login";
	}
}
