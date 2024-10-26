package com.app.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.modelo.Habilidade;
import com.app.crud.repository.HabilidadeRepositorio;

@Service
public class HabilidadeService {

	@Autowired
	private HabilidadeRepositorio habilidadeRepositorio;
	
	public Habilidade criarHabilidade(Habilidade habilidade) {
		return habilidadeRepositorio.save(habilidade);
	}
	
	public List<Habilidade> buscarTodasHabilidades() {
		return habilidadeRepositorio.findAll();
	}
	
}
