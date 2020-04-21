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

import com.wittyhome.module_base.generator.Request;
import com.wittyhome.module_base.task.RequestRepository;
import com.wittyhome.module_base.utils.factory.RequestFactory;

@Controller
public class RequestController 
{
	private static Logger LOG = LoggerFactory.getLogger(RequestController.class);
	
	private RequestRepository repository;
	
	private RequestFactory factory;
	
	@Autowired
	public RequestController(RequestRepository repository, RequestFactory factory) 
	{
		this.repository = repository;
		this.factory = factory;
	}
	
	@GetMapping("/request")
	public String displayRequest(Model model)
	{
		model.addAttribute("requestClasses", factory.getAllRequestClasses());
		
		var requestPrototypes = factory.getAllRequests();
		
		requestPrototypes.forEach(inputRequest -> {
			model.addAttribute(inputRequest.getClass().getSimpleName(), inputRequest);
		});
				
		model.addAttribute("requests", repository.findAll());
				
		return "request";
	}
	
	@PostMapping("/saveRequest")
	public String saveRequest(@RequestParam(name = "requestClassName") String requestClassName,
	WebRequest webRequest, Model model) 
	{		
		Request request = factory.getRequest(requestClassName);
		
		if (Objects.nonNull(request))
		{
			WebRequestDataBinder binder = new WebRequestDataBinder(request);
			binder.bind(webRequest);

			BindingResult result = binder.getBindingResult();
			request = (Request) result.getTarget();
					
			repository.save(request);
			
			LOG.info("Request ({}) with class {} succesfully saved", request, requestClassName);
		}
		else {
			LOG.error("Request with class name {} is not found", requestClassName);		
		}
						
		return "redirect:/request";
	}
}
