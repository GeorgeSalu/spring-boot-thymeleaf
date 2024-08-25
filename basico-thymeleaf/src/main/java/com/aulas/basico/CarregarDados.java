package com.aulas.basico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aulas.basico.modelo.Papel;
import com.aulas.basico.repository.PapelRepository;

@Component
public class CarregarDados implements CommandLineRunner {

	@Autowired
	private PapelRepository papelRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] papeis = {"ADMIN", "USER", "BIBLIOTECARIO"};
		
		for(String papelString : papeis) {
			Papel papel = papelRepository.findByPapel(papelString);
			if(papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
		}

	}

}
