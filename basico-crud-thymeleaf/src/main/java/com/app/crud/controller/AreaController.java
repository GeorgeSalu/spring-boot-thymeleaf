package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.crud.modelo.Area;
import com.app.crud.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@GetMapping("/novo")
	public String novoArea(Model model) {
		Area area = new Area();
		model.addAttribute("novoArea", area);
		return "novo-area";
	}
	
}
