package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

	@Autowired
	private SpringTemplateEngine template;
	
	public void enviarPedidoDeConfirmacaoDeCadastro(String destino,String codigo) {
		
		Context context = new Context();
		context.setVariable("titulo", "Bem vindo a clinica Spring security");
		context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no lnk abaixo");
		context.setVariable("linkConfirmacao", "http://localhost:8080/u/confirmacao/cadastro?codigo="+codigo);
		
		String html = template.process("email/confirmacao", context);
		
		System.out.println(html);
	}
	
}
