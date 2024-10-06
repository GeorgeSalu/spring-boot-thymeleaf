package com.aulas.basico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aulas.basico.modelo.Papel;
import com.aulas.basico.repository.PapelRepository;

public class PapelServiceImpl implements PapelService {

	@Autowired
	private PapelRepository papelRepository;
	
	@Override
	public Papel buscarPapelPorId(Long id) {
		Optional<Papel> opt = papelRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException("Papel com id "+id+" não existe");
		}
	}

	@Override
	public Papel buscarPapel(String papel) {
		Papel pp = papelRepository.findByPapel(papel);
		return pp;
	}

	@Override
	public List<Papel> listarPapel() {
		List<Papel> papeis = papelRepository.findAll();
		return papeis;
	}

}
