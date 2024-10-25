package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.crud.service.HabilidadeService;

@Controller
@RequestMapping("/habilidade")
public class HabilidadeController {

	@Autowired
	private HabilidadeService habilidadeService;
	
}
