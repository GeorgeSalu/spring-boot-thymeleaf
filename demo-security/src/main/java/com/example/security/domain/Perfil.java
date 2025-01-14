package com.example.security.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "perfis")
public class Perfil extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5653058915556058386L;
	
	@Column(name = "descricao", nullable = false, unique = true)
	private String desc;
	
	public Perfil() {
		super();
	}

	public Perfil(Long id) {
		super.setId(id);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
