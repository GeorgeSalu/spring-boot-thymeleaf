package com.mballem.demoajax.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.mballem.demoajax.domain.Categoria;

public class PromocaoDto {

	@NotNull
	private Long id;
	
	@NotBlank(message = "um titulo é requerido")
	private String titulo;
	
	private String descricao;

	@NotBlank(message = "uma imagem é requerida")
	private String linkImagem;
	
	@NotNull(message = "o preco é requerido")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
	
	@NotNull(message = "uma categoria é requerida")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
