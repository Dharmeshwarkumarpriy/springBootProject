package com.practice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		System.out.println("Inside about handler...");
		// putting data in model...
		model.addAttribute("name", "dharam");
		model.addAttribute("currentDate", new Date().toLocaleString());
		return "about";
		// about.html
	}

	// handling iteration...
	@GetMapping("/example-loop")
	public String iterateHandler(Model model) {
		// create a list, set collection...
		List<String> names = List.of("ankit", "laxmi", "karan", "john");
		model.addAttribute("names", names);
		return "iterate";
	}

	// handler for conditional statements...
	@GetMapping("/condition")
	public String ConditionHandler(Model m) {
		
		System.out.println("conditional handler executed...");
		
		m.addAttribute("isActive",false);
		m.addAttribute("gender","F");
		
		List<Integer> list = List.of(12);
		m.addAttribute("mylist",list);
		
		
		
		return "condition";
	}
	
	
}
