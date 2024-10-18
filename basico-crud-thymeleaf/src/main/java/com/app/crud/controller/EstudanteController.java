package com.app.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstudanteController {

	@GetMapping("/")
	public String listarEstudantes() {
		return "/lista-estudantes";
	}
	
	@GetMapping("/novo")
	public String novoEstudante() {
		return "/novo-estudante";
	}
	
}
