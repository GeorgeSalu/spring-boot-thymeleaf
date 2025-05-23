package com.mballem.demoajax.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Emissor {

	private String id = UUID.randomUUID().toString();
	private SseEmitter emitter;
	private LocalDateTime ultimaData;
	
	public Emissor(SseEmitter emitter, LocalDateTime ultimaData) {
		super();
		this.emitter = emitter;
		this.ultimaData = ultimaData;
	}

	public SseEmitter getEmiter() {
		return emitter;
	}

	public void setEmiter(SseEmitter emiter) {
		this.emitter = emiter;
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
