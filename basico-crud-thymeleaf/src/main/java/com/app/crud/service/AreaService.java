package com.app.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.modelo.Area;
import com.app.crud.repository.AreaRepository;

@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	public Area gravar(Area area) {
		return areaRepository.save(area);
	}
	
	public List<Area> listar() {
		return areaRepository.findAll();
	}
	
}
