package com.aulas.basico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aulas.basico.modelo.Papel;
import com.aulas.basico.modelo.Usuario;
import com.aulas.basico.repository.UsuarioRepository;
import com.aulas.basico.service.PapelService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PapelService papelService;
	
	@Autowired
	private BCryptPasswordEncoder criptografia;
	
	@GetMapping("/index")
	public String index(@CurrentSecurityContext(expression = "authentication.name") String login) {
		
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		String redirectURL = "";
		if(temAutorizacao(usuario, "ADMIN")) {
			redirectURL = "/auth/admin/admin-index";
		} else if (temAutorizacao(usuario, "USER")) {
			redirectURL = "/auth/user/user-index";
		} else if (temAutorizacao(usuario, "BIBLIOTECARIO")) {
			redirectURL = "/auth/biblio/biblio-index";
		}

		return redirectURL;
	}
	
	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario,BindingResult result,Model model,RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		
		Usuario usr = usuarioRepository.findByLogin(usuario.getLogin());
		if(usr != null) {
			model.addAttribute("loginJaExiste", "Login ja existe cadastrado");
			return "/publica-criar-usuario";
		}
		
		Papel papel = papelService.buscarPapel("USER");
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(papel);
		usuario.setPapeis(papeis);
		
		String senhaCriptografada = criptografia.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);
		
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
				.orElseThrow(() -> new IllegalArgumentException("id = "+id+" invalido "));
		usuarioRepository.delete(usuario);
		return "redirect:/usuario/admin/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id,Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if(!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("usuario invalido");
		}
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("usuario", usuario);
		return "/auth/user/user-alterar-usuario";
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id,@Valid Usuario usuario,BindingResult result) {
		if(result.hasErrors()) {
			usuario.setId(id);
			return "/auth/user/user-alterar-usuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/usuario/admin/listar";
	}
	
	@GetMapping("/editarPapel/{id}")
	public String selecionarPapel(@PathVariable("id") long id,Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if(!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("usuario invalido");
		}
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaPapeis", papelService.listarPapel());
		return "/auth/admin/admin-editar-papel-usuario";
	}
	
	@PostMapping("/editarPapel/{id}")
	public String atribuirPapel(@PathVariable("id") long idUsuario,
			                    @RequestParam(value = "pps", required = false) int[] pps,
			                    Usuario usuario, 
			                    RedirectAttributes attributes) {
		
		if(pps == null) {
			usuario.setId(idUsuario);
			attributes.addFlashAttribute("mensagem", "Pelo menos uma papel deve ser informado");
			return "redirect:/usuario/editarPapel/"+idUsuario;
		} else {
			// obtem a lista de papeis selecionada pelo usuario do banco
			List<Papel> papeis = new ArrayList<Papel>();
			for(int i = 0;i < pps.length; i++) {
				long idPapel = pps[i];
				Papel papel = papelService.buscarPapelPorId(idPapel);
				papeis.add(papel);
			}
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
			if(usuarioOptional.isPresent()) {
				Usuario usr = usuarioOptional.get();
				usr.setPapeis(papeis); // relaciona papeis ao usuario
				usr.setAtivo(usuario.isAtivo());
				usuarioRepository.save(usr);
			}
		}
		return "redirect:/usuario/admin/listar";
	}
	
	private boolean temAutorizacao(Usuario usuario,String papel) {
		for(Papel pp: usuario.getPapeis()) {
			if(pp.getPapel().equals(papel)) {
				return true;
			}
		}
		return false;
	}
	
}


