package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Medico;
import com.example.security.service.MedicoService;

@Controller
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;

	@GetMapping({"/dados"})
	public String abrirPorMedico(Medico medico,ModelMap model) {
		
		return "medico/cadastro";
	}
	
	@GetMapping({"/salvar"})
	public String salvar(Medico medico,RedirectAttributes attr) {
		
		medicoService.salvar(medico);
		attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
		attr.addFlashAttribute("medico", medico);
		
		return "redirect:/medicos/dados";
	}
	
	@GetMapping({"/editar"})
	public String editar(Medico medico,RedirectAttributes attr) {
		
		medicoService.editar(medico);
		attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
		attr.addFlashAttribute("medico", medico);
		
		return "redirect:/medicos/dados";
	}
	
}
