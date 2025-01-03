package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.domain.Medico;

@Controller
@RequestMapping("medicos")
public class MedicoController {

	@GetMapping({"/dados"})
	public String abrirPorMedico(Medico medico,ModelMap model) {
		
		return "medico/cadastro";
	}
	
}
