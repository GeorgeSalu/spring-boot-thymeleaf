package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.crud.modelo.Estudante;
import com.app.crud.service.EstudanteService;

@Controller
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;

	@GetMapping("/")
	public String listarEstudantes() {
		return "/lista-estudantes";
	}
	
	@GetMapping("/novo")
	public String novoEstudante(Model model) {
		Estudante estudante = new Estudante();
		model.addAttribute("novoEstudante", estudante);
		return "/novo-estudante";
	}
	
	@PostMapping("/gravar")
	public String gracar(@ModelAttribute("novoEstudante") Estudante estudante) {
		estudanteService.criarEstudante(estudante);
		return "redirect:/novo";
	}
	
}
