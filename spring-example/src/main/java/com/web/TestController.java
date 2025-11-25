package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/teste")
	public String action(Model model) {
		model.addAttribute("nome", "george");
		return "teste";
	}
	
}
