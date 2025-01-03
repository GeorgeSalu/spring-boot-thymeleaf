package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Especialidade;
import com.example.security.service.EspecialidadeService;

@Controller
@RequestMapping("especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService service;
	
	@GetMapping({"", "/"})
	public String abrir(Especialidade especialidade) {
		
		return "especialidade/especialidade";
	}
	
	@PostMapping("/salvar")
	public String salvar(Especialidade especialidade, RedirectAttributes attr) {
		service.salvar(especialidade);
		attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
		return "redirect:/especialidades";
	}
	
}
