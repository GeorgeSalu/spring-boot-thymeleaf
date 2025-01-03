package com.mballem.curso.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastro(Departamento departamento) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		service.salvar(departamento);
		attr.addFlashAttribute("success", "departamento inserido com sucesso");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.editar(departamento);
		attr.addFlashAttribute("success", "departamento editado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "departamento não removido, possui cargos vindulados.");
		} else {
			service.excluir(id);
			model.addAttribute("success", "departamento excluido com sucesso.");
		}
		return listar(model);
	}

}
