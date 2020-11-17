package br.com.Kdmeubichinho.dto;

import br.com.Kdmeubichinho.entities.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecieRequestDTO {
	
	private String nome;
	
	public Especie build() {
		Especie especie = new Especie()
				.setNome(this.nome);
		return especie;
	}

}
