package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.domain.Usuario;
import com.example.security.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}
	
}
