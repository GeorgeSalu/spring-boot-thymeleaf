package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.crud.modelo.Habilidade;
import com.app.crud.service.HabilidadeService;

@Controller
@RequestMapping("/habilidade")
public class HabilidadeController {

	@Autowired
	private HabilidadeService habilidadeService;
	
	@GetMapping("/novo")
	public String novahabilidade(Model model) {
		Habilidade habilidade = new Habilidade();
		model.addAttribute("item", habilidade);
		return "/novo-habilidade";
	}
	
}
