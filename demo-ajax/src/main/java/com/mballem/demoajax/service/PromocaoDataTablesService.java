package com.mballem.demoajax.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mballem.demoajax.repository.PromocaoRepository;

public class PromocaoDataTablesService {

	private String[] cols = {
		"id", "titulo", "site", "linkPromocao", "descricao", "linkImagem", "preco", "likes", "dtCadastro", "categoria"	
	};
	
	public Map<String, Object> execute(PromocaoRepository repository,HttpServletRequest request) {
		
		int start = Integer.parseInt(request.getParameter("start"));
		int lenght = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		
		int current = currentPage(start, lenght);
		
		Map<String, Object> json = new LinkedHashMap<String, Object>();
		json.put("draw", null);
		json.put("recordsTotal", 0);
		json.put("recordsFiltered", 0);
		json.put("data", null);
		
		return json;
	}

	private int currentPage(int start, int lenght) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
