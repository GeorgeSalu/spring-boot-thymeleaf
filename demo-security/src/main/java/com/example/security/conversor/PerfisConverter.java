package com.example.security.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.security.domain.Perfil;

@Component
public class PerfisConverter implements Converter<String[], List<Perfil>>{

	@Override
	public List<Perfil> convert(String[] source) {
		
		List<Perfil> perfis = new ArrayList<Perfil>();
		
		for(String id: source) {
			if(!id.equals("0")) {
				perfis.add(new Perfil(Long.parseLong(id)));
			}
		}
		
		return perfis;
	}

}
