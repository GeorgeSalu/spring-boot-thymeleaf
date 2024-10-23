package com.app.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.crud.exception.EstudanteNotFoundException;
import com.app.crud.modelo.Area;
import com.app.crud.modelo.Estudante;
import com.app.crud.service.AreaService;
import com.app.crud.service.EstudanteService;

import jakarta.validation.Valid;

@Controller
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private AreaService areaService;

	@GetMapping("/")
	public String listarEstudantes(Model model) {
		List<Estudante> estudantes = estudanteService.buscarTodosEstudantes();
		model.addAttribute("listaEstudantes", estudantes);
		return "/lista-estudantes";
	}
	
	@GetMapping("/novo")
	public String novoEstudante(Model model) {
		model.addAttribute("novoEstudante", new Estudante());
		
		List<Area> areas = areaService.listar();
		model.addAttribute("areas", areas);
		
		return "/novo-estudante";
	}
	
	@PostMapping("/buscar")
	public String buscarEstudantes(Model model, @Param("nome") String nome) {
		if (nome == null) {
			return "redirect:/";
		}
		List<Estudante> estudantes = estudanteService.buscarTodosEstudantesPorNome(nome);
		model.addAttribute("listaEstudantes", estudantes);
		return "/lista-estudantes";
	}
	
	@PostMapping("/gravar")
	public String gravarEstudante(@ModelAttribute("novoEstudante") @Valid Estudante estudante,
								  BindingResult erros,RedirectAttributes attributes,Model model) {
		
		if(erros.hasErrors()) {
			List<Area> areas = areaService.listar();
			model.addAttribute("areas", areas);
			return "/novo-estudante";
		}
		
		estudanteService.criarEstudante(estudante);
		attributes.addFlashAttribute("mensagem", "Estudante salvo com sucesso!");
		return "redirect:/novo";
	}

	@GetMapping("/apagar/{id}")
	public String apagarEstudante(@PathVariable("id") long id,RedirectAttributes attributes) {
		try {
			estudanteService.apagarEstudante(id);
		} catch (EstudanteNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarForm(@PathVariable("id") long id,RedirectAttributes attributes,Model model) {
		try {
			Estudante estudante = estudanteService.buscarEstudantePorId(id);
			model.addAttribute("objetoEstudante", estudante);
			return "alterar-estudante";
		} catch (EstudanteNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}
	
	@PostMapping("/editar/{id}")
	public String editarEstudante(@PathVariable("id") long id,
								  @ModelAttribute("objetoEstudante") @Valid Estudante estudante,
								  BindingResult erros) {
		
		if (erros.hasErrors()) {
			estudante.setId(id);
			return "/alterar-estudante";
		}
		
		estudanteService.alterarEstudante(estudante);
		
		return "redirect:/";
	}
	
}


