package br.com.Kdmeubichinho.enums;

import java.util.stream.Stream;

public enum AnimalSexo {
	MACHO("Macho"), FEMEA("Fêmea"), NAO_SEI("Não sei");
	
	private String descricao;
	
	private AnimalSexo(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public static AnimalSexo of(String descricao) {
		  return Stream.of(AnimalSexo.values())
		    .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
		}
}
