package com.demo.freemarker.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@GetMapping
	public ModelAndView get() {
		return new ModelAndView("index").addObject("currentDate", LocalDate.now());
	}
	
}
