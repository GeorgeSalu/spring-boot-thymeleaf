package com.example.security.controller;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Medico;
import com.example.security.domain.Perfil;
import com.example.security.domain.PerfilTipo;
import com.example.security.domain.Usuario;
import com.example.security.service.MedicoService;
import com.example.security.service.UsuarioService;

@Controller
@RequestMapping("u")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MedicoService medicoService;

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
		
		return ResponseEntity.ok(usuarioService.buscarTodos(request));
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
				usuarioService.salvarUsuario(usuario);
				attr.addFlashAttribute("sucesso", "Operacao realizada com sucesso");
			} catch (DataIntegrityViolationException e) {
				attr.addFlashAttribute("falha", "Cadastro nao realizado, email ja existente");
			}

		}
		
		return "redirect:/u/novo/cadastro/usuario";
	}
	
	@GetMapping("/editar/credenciais/usuario/{id}")
	public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {
		
		return new ModelAndView("usuario/cadastro", "usuario", usuarioService.buscarPorId(id));
	}
	
	@GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
	public ModelAndView preEditarCadastroDadosPessoais(@PathVariable("id") Long usuarioId,
													   @PathVariable("perfis") Long[] perfisId) {
		
		Usuario us = usuarioService.buscarPorIdEPerfis(usuarioId, perfisId);
		
		if(us.getPerfis().contains(new Perfil(PerfilTipo.ADMIN.getCod())) && !us.getPerfis().contains(new Perfil(PerfilTipo.MEDICO.getCod())) ) {
			
			return new ModelAndView("usuario/cadastro", "usuario", us);
		} else if (us.getPerfis().contains(new Perfil(PerfilTipo.MEDICO.getCod()))) {
			
			Medico medico = medicoService.buscarPorUsuarioId(usuarioId);
			
			return medico.hasNotId() 
					? new ModelAndView("medico/cadastro", "medico", new Medico(new Usuario(usuarioId)))
					: new ModelAndView("medico/cadastro", "medico", medico);
			
		} else if (us.getPerfis().contains(new Perfil(PerfilTipo.PACIENTE.getCod()))) {
			
			ModelAndView model = new ModelAndView("error");
			model.addObject("status", 403);
			model.addObject("error", "Area restrita");
			model.addObject("message", "Os dados de pacientes são restritos a ele.");
			
			return model;
		}
		
		return new ModelAndView("redirect:/u/lista");
	}
	
	@GetMapping("/editar/senha")
	public String abrirEditarSenha() {
		
		return "usuario/editar-senha";
	}
	
	@PostMapping("/confirmar/senha")
	public String editarSenha(@RequestParam("senha1") String s1,@RequestParam("senha2") String s2,
			            @RequestParam("senha3") String s3,@AuthenticationPrincipal User user, 
			            RedirectAttributes attr) {
		
		if(!s1.equals(s2)) {
			attr.addFlashAttribute("falha", "Senhas não conferem, tente novamente");
			return "redirect:/u/editar/senha";
		}
		
		Usuario u = usuarioService.buscarPorEmail(user.getUsername());
		
		if(!UsuarioService.isSenhaCorreta(s3, u.getSenha())) {
			attr.addFlashAttribute("falha", "Senhas atual nao confere, tente novamente");
			return "redirect:/u/editar/senha";
		}
		
		usuarioService.alterarSenha(u, s1);
		attr.addFlashAttribute("sucesso", "Senhas alterada com sucesso");
		
		return "redirect:/u/editar/senha";
	}
	
	@GetMapping("/novo/cadastro")
	public String novoCadastro(Usuario usuario) {
		
		return "cadastrar-se";
	}
	
	@GetMapping("/cadastro/realizado")
	public String cadastroRealizado() {
		
		return "fragments/mensagem";
	}
	
	@PostMapping("/cadastro/paciente/salvar")
	public String salvarCadastroPaciente(Usuario usuario,BindingResult result) {
		try {
			usuarioService.salvarCadastroPaciente(usuario);
		} catch (DataIntegrityViolationException ex) {
			result.reject("email", "Ops.... este email ja existe na base de dados");
			return "cadastrar-se";
		}
		return "redirect:/u/cadastro/realizado";
	}
	
	@GetMapping("/confirmacao/cadastro")
	public String respostaConfirmacaoCadastroPaciente(@RequestParam("codigo") String codigo,RedirectAttributes attr) {
		
		usuarioService.ativarCadastroPaciente(codigo);
		
		attr.addFlashAttribute("alerta", "sucesso");
		attr.addFlashAttribute("titulo", "Cadastro ativado");
		attr.addFlashAttribute("texto", "Parabens, seu cadastro esta ativo");
		attr.addFlashAttribute("subtexto", "Siga com seu login/senha");
		
		return "redirect:/login";
	}
	
	@GetMapping("/p/redefinir/senha")
	public String pedidoRecuperarSenha() {
		
		return "usuario/pedido-recuperar-senha";
	}
	
	@GetMapping("/p/recuperar/senha")
	public String redefinirSenha(String email,ModelMap model) {
		
		usuarioService.pedidoRedefinicaoDeSenha(email);
		model.addAttribute("sucesso", "Em instantes vc recebera um email para prosseguir com a redefinicao de sua senha");
		model.addAttribute("usuario", new Usuario(email));
		
		return "usuario/recuperar-senha";
	}
	
	@PostMapping("/p/nova/senha")
	public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario,ModelMap model) {
		
		Usuario u = usuarioService.buscarPorEmail(usuario.getEmail());
		
		if(!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
			model.addAttribute("falha", "Codigo verificador nao confere");
			return "usuario/recuperar-senha";
		}
		u.setCodigoVerificador(null);
		usuarioService.alterarSenha(u, usuario.getSenha());
		model.addAttribute("alerta", "sucesso");
		model.addAttribute("titulo", "senha redefinida");
		model.addAttribute("texto", "voce ja pode logar no sistema");
		
		return "login";
	}
	
}













