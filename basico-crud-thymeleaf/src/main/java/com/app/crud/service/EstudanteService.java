package com.app.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.modelo.Estudante;
import com.app.crud.repository.EstudanteRepository;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepository;
	
	public Estudante criarEstudante(Estudante estudante) {
		return estudanteRepository.save(estudante);
	}
	
	public List<Estudante> buscarTodosEstudantes() {
		return estudanteRepository.findAll();
	}
	
}
