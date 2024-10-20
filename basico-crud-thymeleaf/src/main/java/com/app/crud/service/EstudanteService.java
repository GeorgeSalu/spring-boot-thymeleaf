package com.app.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.exception.EstudanteNotFoundException;
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
	
	public Estudante buscarEstudantePorId(Long id) throws EstudanteNotFoundException {
		Optional<Estudante> opt = estudanteRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new EstudanteNotFoundException("Estudante com "+id+" nao existe");
		}
	}
	
	public void apagarEstudante(Long id) throws EstudanteNotFoundException {
		Estudante estudante = buscarEstudantePorId(id);
		estudanteRepository.delete(estudante);
	}
	
}


