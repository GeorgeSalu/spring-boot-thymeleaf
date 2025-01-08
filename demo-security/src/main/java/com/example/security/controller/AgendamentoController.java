package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.domain.Agendamento;

@Controller
@RequestMapping("agendamentos")
public class AgendamentoController {

	@GetMapping({"/agendar"})
	public String agendarConsulta(Agendamento agendamento) {
		
		return "agendamento/cadastro";
	}
	
}
