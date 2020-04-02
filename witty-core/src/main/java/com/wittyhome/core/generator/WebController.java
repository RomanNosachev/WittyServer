package com.wittyhome.core.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wittyhome.module_base.dispatcher.Dispatcher;
import com.wittyhome.module_base.generator.Generator;

@Controller
@RequestMapping({"/", "index"})
public class WebController 
implements Generator<StringRequest>
{
	private static Logger LOG = LoggerFactory.getLogger(WebController.class);
	
	private Dispatcher dispatcher;
	
	@Autowired
	public WebController(Dispatcher dispatcher) 
	{
		this.dispatcher = dispatcher;
	}

	@GetMapping
	public String index()
	{		
		StringRequest request = new StringRequest("rainbow");
		
		LOG.info("StringRequest generated: {}", request.getName());
		
		generate(request);
		
		return "index";
	}

	@Override
	public void generate(StringRequest request) 
	{
		dispatcher.dispatch(request);		
	}
}
