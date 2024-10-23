package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.crud.exception.EstudanteNotFoundException;
import com.app.crud.modelo.Endereco;
import com.app.crud.modelo.Estudante;
import com.app.crud.service.EnderecoService;
import com.app.crud.service.EstudanteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EstudanteService estudanteService;
	
	@GetMapping("/buscar-endereco/{id}")
	public String novoEndereco(@PathVariable("id") long id,Model model) {
		String pagina = "";
		try {
			Estudante estudante = estudanteService.buscarEstudantePorId(id);
			if(estudante.getEndereco() == null) {
				Endereco endereco = new Endereco();
				endereco.setEstudante(estudante);
				model.addAttribute("item", endereco);
				pagina = "/novo-endereco";
			} else {
				pagina = "redirect:/";
			}
		} catch (EstudanteNotFoundException e) {
			e.printStackTrace();
		}
		
		return pagina;
	}
	
	@PostMapping("/gravar-endereco/{idEstudante}")
	public String gravarEndereco(@PathVariable("idEstudante") long idEstudante,
			@ModelAttribute("item") @Valid Endereco endereco, BindingResult result, RedirectAttributes attributes) {
		
		try {
			Estudante estudante = estudanteService.buscarEstudantePorId(idEstudante);
			endereco.setEstudante(estudante);
		} catch (EstudanteNotFoundException e) {
			e.printStackTrace();
		}
		if(result.hasErrors()) {
			return "novo-endereco";
		}
		enderecoService.salvar(endereco);
		attributes.addFlashAttribute("mensagem", "Endereco salvo com sucesso");
		
		return "redirect:/endereco/buscar-endereco/"+idEstudante;
	}
	
}




