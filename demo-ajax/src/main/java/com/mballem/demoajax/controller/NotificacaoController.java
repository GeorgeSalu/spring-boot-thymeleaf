package com.mballem.demoajax.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class NotificacaoController {

	@GetMapping("/promocao/notificacao")
	public SseEmitter enviarNotificaicao() throws IOException {
		
		SseEmitter emitter = new SseEmitter(0L);
		emitter.send("Ola mundo");
		return emitter;
	}
	
}
