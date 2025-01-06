package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.domain.Medico;
import com.example.security.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;
	
	@Transactional(readOnly = true)
	public Medico buscarPorUsuarioId(Long id) {
		
		return repository.findByUsuarioId(id)
					.orElse(new Medico());
	}

	@Transactional(readOnly = true)
	public void salvar(Medico medico) {
		
		repository.save(medico);
	}

	@Transactional(readOnly = true)
	public void editar(Medico medico) {

		Medico m2 = repository.findById(medico.getId()).get();
		m2.setCrm(medico.getCrm());
		m2.setDtInscricao(medico.getDtInscricao());
		m2.setNome(medico.getNome());
		
		if(!medico.getEspecialidades().isEmpty()) {
			
			m2.getEspecialidades().addAll(medico.getEspecialidades());
		}
		
	}
	
}
