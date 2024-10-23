package com.app.crud.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome deve ser informado")
	@Size(min = 2, message = "O nome do aluno deve ter no mínimo 2 caracteres")
	private String nome;

	@Min(value = 18, message = "O aluno deve ter no mínimo 18 anos")
	private int idade;

	@OneToOne(mappedBy = "estudante", cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToOne
	private Area area;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Estudante [id=" + id + ", nome=" + nome + ", idade=" + idade + ", endereco=" + endereco + ", area="
				+ area + "]";
	}

}
