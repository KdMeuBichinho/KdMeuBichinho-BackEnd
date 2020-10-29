package br.com.Kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.Kdmeubichinho.enums.AnimalTipo;

@Converter(autoApply = true)
public class AnimalTipoConverter implements AttributeConverter<AnimalTipo, String> {
	
	@Override
	public String convertToDatabaseColumn(AnimalTipo tipo) {
		if(tipo == null) {
			return null;
		}
		return tipo.getDescricao();
	}
	
	@Override
	public AnimalTipo convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnimalTipo.of(descricao);
	}

}
