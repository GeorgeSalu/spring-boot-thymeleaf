package com.app.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.crud.modelo.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{

	List<Estudante> findByNomeContainingIgnoreCase(String nome);
	
}
