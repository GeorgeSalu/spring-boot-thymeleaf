package com.example.security.conversor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.security.domain.Especialidade;
import com.example.security.service.EspecialidadeService;

@Component
public class EspecialidadesConverter implements Converter<String[], Set<Especialidade>>{

	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Override
	public Set<Especialidade> convert(String[] titulos) {

		Set<Especialidade> especialidades = new HashSet<Especialidade>();
		
		if(titulos != null && titulos.length > 0) {
			especialidades.addAll(especialidadeService.buscarPorTitulo(titulos));
		}
		
		return especialidades;
	}

	
	
}
