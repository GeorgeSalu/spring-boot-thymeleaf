package com.aulas.basico.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.aulas.basico.modelo.Papel;
import com.aulas.basico.modelo.Usuario;
import com.aulas.basico.repository.UsuarioRepository;

@Component
public class LoginSucesso extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		// pega o login do usuario logado
		String login = authentication.getName();
		// busca usuario no banco pelo login
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		String redirectURL = "";
		if(temAutorizacao(usuario, "ADMIN")) {
			redirectURL = "/auth/admin/admin-index";
		} else if (temAutorizacao(usuario, "USER")) {
			redirectURL = "/auth/user/user-index";
		} else if (temAutorizacao(usuario, "BIBLIOTECARIO")) {
			redirectURL = "/auth/biblio/biblio-index";
		}
		response.sendRedirect(redirectURL);
	}
	
	// verifica qual papel o usuario tem na aplicacao
	private boolean temAutorizacao(Usuario usuario,String papel) {
		for(Papel pp: usuario.getPapeis()) {
			if(pp.getPapel().equals(papel)) {
				return true;
			}
		}
		return false;
	}
	
}
