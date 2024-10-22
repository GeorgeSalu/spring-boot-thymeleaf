package com.app.crud.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O logradouro deve ser informado")
	@Size(min = 2, message = "O logradouro deve ter no mínimo 2 caracteres")
	private String logradouro;

	@NotBlank(message = "O numero deve ser informado")
	@Size(min = 2, message = "O numero deve ter no mínimo 2 caracteres")
	private String numero;

	@NotBlank(message = "O cep deve ser informado")
	@Size(min = 8, message = "O cep deve ter no mínimo 8 caracteres")
	private String cep;

	@OneToOne
	private Estudante estudante;

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep
				+ ", estudante=" + estudante + "]";
	}

}
