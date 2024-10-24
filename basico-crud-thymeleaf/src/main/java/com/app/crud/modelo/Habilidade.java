package com.app.crud.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Habilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O descrição deve ser informado")
	@Size(min = 2, message = "O descrição deve ter no mínimo 2 caracteres")
	private String descricao;

	@ManyToMany(mappedBy = "habilidades")
	private List<Estudante> estudantes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

	@Override
	public String toString() {
		return "Habilidade [id=" + id + ", nome=" + nome + ", estudantes=" + estudantes + "]";
	}

}
