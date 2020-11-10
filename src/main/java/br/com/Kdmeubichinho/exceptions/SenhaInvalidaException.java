package br.com.Kdmeubichinho.exceptions;

public class SenhaInvalidaException extends RuntimeException{
	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}