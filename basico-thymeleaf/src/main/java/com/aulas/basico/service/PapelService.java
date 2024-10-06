package com.aulas.basico.service;

import java.util.List;

import com.aulas.basico.modelo.Papel;

public interface PapelService {

	public Papel buscarPapelPorId(Long id);
	public Papel buscarPapel(String papel);
	public List<Papel> listarPapel();
	
}
