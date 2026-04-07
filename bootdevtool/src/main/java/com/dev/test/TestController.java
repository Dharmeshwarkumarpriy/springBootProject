package com.dev.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		
		int a=78;
		int b=7556;
		int c=534;
		return "this is just testing  sum of a and b and c is "+(a+b+c);
	}
	
}
