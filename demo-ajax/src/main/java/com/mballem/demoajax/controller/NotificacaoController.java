package com.mballem.demoajax.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.mballem.demoajax.domain.Emissor;
import com.mballem.demoajax.repository.PromocaoRepository;

@Controller
public class NotificacaoController {

	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@GetMapping("/promocao/notificacao")
	public SseEmitter enviarNotificaicao() throws IOException {
		
		SseEmitter emitter = new SseEmitter(0L);

		Emissor emissor = new Emissor(emitter, getDtCadastroUltimaPromocao());
		emissor.getEmiter().send(emissor.getUltimaData());
		
		return emitter;
	}
	
	private LocalDateTime getDtCadastroUltimaPromocao() {
		return promocaoRepository.findPromocaoComUltimaData();
	}
	
}
