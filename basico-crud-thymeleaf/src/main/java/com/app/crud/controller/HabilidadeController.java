package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.crud.modelo.Habilidade;
import com.app.crud.service.HabilidadeService;

import jakarta.validation.Valid;

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
	
	@PostMapping("/gravar")
	public String gravarHabilidade(@ModelAttribute("item") @Valid Habilidade habilidade,
								   BindingResult erros, RedirectAttributes attributes) {
	
		if(erros.hasErrors()) {
			return "/novo-habilidade";
		}
		habilidadeService.criarHabilidade(habilidade);
		attributes.addFlashAttribute("mensagem", "Habilidade salva com sucesso !");
		return "redirect:/habilidade/novo";
	}
	
}
