package br.com.Kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.Kdmeubichinho.enums.AnuncioStatus;

@Converter(autoApply = true)
public class AnuncioStatusConverter implements AttributeConverter<AnuncioStatus, String> {
	
	@Override
	public String convertToDatabaseColumn(AnuncioStatus status) {
		if(status == null) {
			return null;
		}
		return status.getDescricao();
	}
	
	@Override
	public AnuncioStatus convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnuncioStatus.of(descricao);
	}

}
