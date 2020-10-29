package br.com.Kdmeubichinho.enums;

public enum AnimalSexo {
	MACHO("Macho"), FEMEA("Fêmea"), NAO_SEI("Não sei");
	
	private String descricao;
	
	private AnimalSexo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
