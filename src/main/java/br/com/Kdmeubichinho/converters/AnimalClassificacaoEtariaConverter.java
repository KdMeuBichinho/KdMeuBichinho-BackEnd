package br.com.Kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;

@Converter(autoApply = true)
public class AnimalClassificacaoEtariaConverter implements AttributeConverter<AnimalClassificacaoEtaria, String> {
	
	@Override
	public String convertToDatabaseColumn(AnimalClassificacaoEtaria classificacaoEtaria) {
		if(classificacaoEtaria == null) {
			return null;
		}
		return classificacaoEtaria.getDescricao();
	}
	
	@Override
	public AnimalClassificacaoEtaria convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnimalClassificacaoEtaria.of(descricao);
	}

}
