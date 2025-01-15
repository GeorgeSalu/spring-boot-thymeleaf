package com.demo.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.freemarker.model.UserForm;

@Controller
public class FormController {

	@GetMapping("/")
	public String index() {
		return "redirect:/form";
	}
	
	@GetMapping("/form")
	public String formGet() {
		return "form/form";
	}
	
	@PostMapping("/form")
	public String formPost(UserForm user,Model model) {
		model.addAttribute("user", user);
		return "form/form";
	}
	
}
