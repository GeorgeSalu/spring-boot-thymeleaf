package com.aulas.basico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aulas.basico.modelo.Usuario;
import com.aulas.basico.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario,BindingResult result,RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "usuario salvo com sucesso");
		return "redirect:/usuario/novo";
	}
	
	@GetMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll() );
		return "/auth/admin/admin-listar-usuario";
	}
	
	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id,Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id invalido "+id));
		usuarioRepository.delete(usuario);
		return "redirect:/usuario/admin/listar";
	}
	
	
}
