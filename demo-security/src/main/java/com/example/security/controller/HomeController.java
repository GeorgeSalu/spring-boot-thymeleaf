package com.example.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
	
	@GetMapping({"/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/login-error"})
	public String loginError(ModelMap model, HttpServletRequest resp) {
		
		HttpSession session = resp.getSession();
		String lastException = String.valueOf(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));

		if(lastException.contains(SessionAuthenticationException.class.getName())) {
			model.addAttribute("alerta", "erro");
			model.addAttribute("titulo", "Acesso recusado!");
			model.addAttribute("texto", "Você já esta logado em outro dispositivo.");
			model.addAttribute("subtexto", "Faça o logout ou espere sua sessão expirar.");
			return "login";
		}
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Credenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		model.addAttribute("subtexto", "Acesso permitido apenas para cadstro já ativados.");
		return "login";		
	}
	
	@GetMapping({"/expired"})
	public String sessaoExpirada(ModelMap model) {
		
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Acesso recusado");
		model.addAttribute("texto", "Sua sessao expirou");
		model.addAttribute("subtexto", "Voce logou em outro dispositivo");
		return "login";		
	}
	
	@GetMapping({"/acesso-negado"})
	public String acessoNegado(ModelMap model, HttpServletResponse resp) {
		model.addAttribute("status", resp.getStatus());
		model.addAttribute("error", "Acesso Negado");
		model.addAttribute("message", "Voce não tem permissao para acesso a esta area ou ação");
		return "error";
	}
}
