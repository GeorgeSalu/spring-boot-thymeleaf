package com.aulas.basico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulas.basico.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByLogin(String login);
	
}
