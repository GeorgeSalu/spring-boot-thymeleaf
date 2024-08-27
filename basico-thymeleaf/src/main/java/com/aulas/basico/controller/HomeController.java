package com.aulas.basico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("msgBemVindo", "Bem-vindo à biblioteca");
		return "publica-index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
