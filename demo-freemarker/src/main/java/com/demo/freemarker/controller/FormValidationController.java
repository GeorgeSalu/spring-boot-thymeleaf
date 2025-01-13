package com.demo.freemarker.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.freemarker.model.Person;

@Controller
@RequestMapping("valid")
public class FormValidationController {

	@GetMapping("/")
	public String index() {
		return "redirect:/valid/form";
	}
	
	@GetMapping("/form")
	public String formGet(Model model) {
		model.addAttribute("person", new Person());
		return "formValid/form";
	}
	
	@PostMapping("/form")
	public String formPost(@Valid Person user,BindingResult bindingResult,Model model) {
		if(!bindingResult.hasErrors()) {
			model.addAttribute("noErrors", true);
		}
		model.addAttribute("person", user);
		return "formValid/form";
	}
	
}
