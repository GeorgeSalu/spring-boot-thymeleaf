package com.example.security.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Perfil;
import com.example.security.domain.Usuario;
import com.example.security.service.UsuarioService;

@Controller
@RequestMapping("u")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@GetMapping("/novo/cadastro/usuario")
	public String cadastroPorAdminParaAdminMedicoPaciente(Usuario usuario) {
		
		return "usuario/cadastro";
	}

	@GetMapping("/lista")
	public String listartUsuarios() {
		
		return "usuario/lista";
	}
	
	@GetMapping("/datatables/server/usuarios")
	public ResponseEntity<?> listarUsuarioDatatables(HttpServletRequest request) {
		
		return ResponseEntity.ok(service.buscarTodos(request));
	}
	
	@PostMapping("/cadastro/salvar")
	public String salvarUsuarios(Usuario usuario,RedirectAttributes attr) {
		
		List<Perfil> perfis = usuario.getPerfis();
		
		if(perfis.size() > 2 || perfis.containsAll(Arrays.asList(new Perfil(1L), new Perfil(3L))) || 
				perfis.containsAll(Arrays.asList(new Perfil(2L), new Perfil(3L)))) {
			
			attr.addFlashAttribute("falha", "Paciente nao pode ser Admin e ou Medico");
			attr.addFlashAttribute("usuario", usuario);
			
		} else {
			
			try {
				service.salvarUsuario(usuario);
				attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
			} catch (DataIntegrityViolationException e) {
				attr.addFlashAttribute("falha", "Cadastro nao realizado, email ja existente");
			}

		}
		
		return "redirect:/u/novo/cadastro/usuario";
	}
	
	@GetMapping("/editar/credenciais/usuario/{id}")
	public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {
		
		return new ModelAndView("usuario/cadastro", "usuario", service.buscarPorId(id));
	}
	
}













