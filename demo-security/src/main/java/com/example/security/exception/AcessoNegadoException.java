package com.example.security.exception;

public class AcessoNegadoException extends RuntimeException {

	private static final long serialVersionUID = -5194742079941229674L;

	public AcessoNegadoException(String mensagem) {
		super(mensagem);
	}
	
}
