package com.example.security.domain;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "horas", indexes = {@Index(name = "idx_hora_minuto", columnList = "hora_minuto")})
public class Horario extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6128754312483672045L;
	
	@Column(name = "hora_minuto", unique = true, nullable = false)
	private LocalTime horaMinuto;

	public LocalTime getHoraMinuto() {
		return horaMinuto;
	}

	public void setHoraMinuto(LocalTime horaMinuto) {
		this.horaMinuto = horaMinuto;
	}
}
