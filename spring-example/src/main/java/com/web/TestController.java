package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@GetMapping("/teste")
	public String action(Model model) {
		model.addAttribute("nome", "george");
		return "teste";
	}

    @GetMapping("/teste2")
    public ModelAndView action2() {
        var modelAndView = new ModelAndView("teste");
        modelAndView.addObject("nome", "treinaweb");
        return modelAndView;
    }
	
}
