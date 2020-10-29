package br.com.Kdmeubichinho.enums;

public enum AnimalIdade {
	FILHOTE("Filhote"), ADULTO("Adulto");
	
	private String descricao;
	
	private AnimalIdade(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
