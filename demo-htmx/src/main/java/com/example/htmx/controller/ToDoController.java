package com.example.htmx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.htmx.services.ToDoService;

@Controller
public class ToDoController {

	private final ToDoService toDoService;

	public ToDoController(ToDoService toDoService) {
		super();
		this.toDoService = toDoService;
	}
	
	@GetMapping("/todos")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("todos/list");
		mv.addObject("toDos", toDoService.list());
		return mv;
	}
	
	@GetMapping("/todos/new")
	public String init() {
		return "todos/new";
	}
	
}
