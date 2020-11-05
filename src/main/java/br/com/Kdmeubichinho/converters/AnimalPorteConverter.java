package br.com.Kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.Kdmeubichinho.enums.AnimalPorte;

@Converter(autoApply = true)
public class AnimalPorteConverter implements AttributeConverter<AnimalPorte, String> {

	
	@Override
	public String convertToDatabaseColumn(AnimalPorte porte) {
		if(porte == null) {
			return null;
		}
		return porte.getDescricao();
	}
	
	@Override
	public AnimalPorte convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnimalPorte.of(descricao);
	}

}
