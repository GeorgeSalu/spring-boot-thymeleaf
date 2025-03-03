package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Medico;
import com.example.security.domain.Usuario;
import com.example.security.service.MedicoService;
import com.example.security.service.UsuarioService;

@Controller
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping({"/dados"})
	public String abrirPorMedico(Medico medico,ModelMap model, @AuthenticationPrincipal User user) {
		
		if(medico.hasNotId()) {
			medico = medicoService.buscarPorEmail(user.getUsername());
			model.addAttribute("medico", medico);
		}
		
		return "medico/cadastro";
	}
	
	@PostMapping({"/salvar"})
	public String salvar(Medico medico,RedirectAttributes attr, @AuthenticationPrincipal User user) {
		
		if(medico.hasNotId() && medico.getUsuario().hasNotId()) {
			Usuario usuario = usuarioService.buscarPorEmail(user.getUsername());
			medico.setUsuario(usuario);
		}
		
		medicoService.salvar(medico);
		attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
		attr.addFlashAttribute("medico", medico);
		
		return "redirect:/medicos/dados";
	}
	
	@PostMapping({"/editar"})
	public String editar(Medico medico,RedirectAttributes attr) {
		
		medicoService.editar(medico);
		attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
		attr.addFlashAttribute("medico", medico);
		
		return "redirect:/medicos/dados";
	}
	
	@GetMapping({"/id/{idMed}/excluir/especializacao/{idEsp}"})
	public String excluirEspecialidadePorMedico(@PathVariable("idMed") Long idMed,
												@PathVariable("idEsp") Long idEsp,RedirectAttributes attr) {
		
		if(medicoService.existeEspecialidadeAgendada(idMed, idEsp)) {
			attr.addFlashAttribute("falha", "Tem consultas agendadas, exclusao negada");
		} else {
			medicoService.excluirEspecialidadePorMedico(idMed, idEsp);
			attr.addFlashAttribute("sucesso", "Especialidade removida com sucesso");
		}
		
		return "redirect:/medicos/dados";
	}
	
	@GetMapping("/especialidade/titulo/{titulo}")
	public ResponseEntity<?> getMedicosPorEspecialidade(@PathVariable("titulo") String titulo) {
		
		return ResponseEntity.ok(medicoService.buscarMedicoPorEspecialidade(titulo));
	}
	
}







