package br.com.Kdmeubichinho.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.Kdmeubichinho.enums.AnimalIdade;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;
import br.com.Kdmeubichinho.enums.AnimalTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal", schema = "kdmeubichinho")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_animal;
	private AnimalTipo tipo;
	@Enumerated(value = EnumType.STRING)
	private AnimalSexo sexo;
	@Enumerated(value = EnumType.STRING)
	private AnimalIdade idade;
	@Enumerated(value = EnumType.STRING)
	private AnimalPorte porte;
	private Boolean castrado;
	private Boolean vacinado;
	private String nome;
	private String cep;
	
	public String getTipo() {
		return tipo.getDescricao();
	}
	public String getSexo() {
		return sexo.getDescricao();
	}

	public String getIdade() {
		return idade.getDescricao();
	}
	public String getPorte() {
		return porte.getDescricao();
	}
	
	
	
//	public String validaTipoAnimal(AnimalTipo tipo) {
//		return tipo.getAnimal();
//	}
//	public void validaSexoAnimal(String sexo) {
//		switch(sexo){
//			case "Macho":
//				setSexo(AnimalSexo.MACHO);
//				break;
//			case "Fêmea":
//				setSexo(AnimalSexo.FEMEA);
//				break;
//			default:
//				setSexo(AnimalSexo.NAO_SEI);				
//		}
//	}
//	public void validaIdadeAnimal(String idade) {
//		switch(idade){
//			case "Filhote":
//				setIdade(AnimalIdade.FILHOTE);
//				break;
//			case "Adulto":
//				setIdade(AnimalIdade.ADULTO);
//				break;			
//		}
//	}	
//	public void validaPorteAnimal(String porte) {
//		switch(porte){
//			case "Pequeno":
//				setPorte(AnimalPorte.PEQUENO);
//				break;
//			case "Médio":
//				setPorte(AnimalPorte.MEDIO);
//				break;	
//			case "Grande":
//				setPorte(AnimalPorte.GRANDE);
//				break;		
//		}
//	}	
}
