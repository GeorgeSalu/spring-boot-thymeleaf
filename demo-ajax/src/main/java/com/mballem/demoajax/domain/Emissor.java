package com.mballem.demoajax.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Emissor {

	private String id = UUID.randomUUID().toString();
	
	private SseEmitter emiter;
	
	private LocalDateTime ultimaData;
	
	public Emissor(SseEmitter emiter, LocalDateTime ultimaData) {
		super();
		this.emiter = emiter;
		this.ultimaData = ultimaData;
	}

	public SseEmitter getEmiter() {
		return emiter;
	}

	public void setEmiter(SseEmitter emiter) {
		this.emiter = emiter;
	}

	public LocalDateTime getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDateTime ultimaData) {
		this.ultimaData = ultimaData;
	}
	
	public String getId() {
		return id;
	}
	
}
