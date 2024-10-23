package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.crud.modelo.Area;
import com.app.crud.service.AreaService;

import jakarta.validation.Valid;

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
	
	@PostMapping("/gravar")
	public String gravarArea(@ModelAttribute("novoArea") @Valid Area area,
							 BindingResult erros,
							 RedirectAttributes attributes) {
		
		if (erros.hasErrors()) {
			return "novo-area";
		}
		areaService.gravar(area);
		attributes.addFlashAttribute("mensagem", "Area salva com sucesso");
		return "redirect:/area/novo";
	}
	
}






