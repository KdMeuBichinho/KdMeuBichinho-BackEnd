package br.com.Kdmeubichinho.enums;

public enum AnimalPorte {
	P("Pequeno"), M("Médio"), G("Grande");
	
	private String descricao;
	
	private AnimalPorte(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
