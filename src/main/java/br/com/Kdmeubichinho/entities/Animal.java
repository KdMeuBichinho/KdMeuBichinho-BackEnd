package br.com.Kdmeubichinho.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.Kdmeubichinho.enums.AnimalIdade;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_animal;
	private String tipo;
	private AnimalSexo sexo;
	private AnimalIdade idade;
	private AnimalPorte porte;
	private Boolean castrado;
	private Boolean vacinado;
	private String nome;
	private String cep;
	
	public Animal() {};
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public AnimalSexo getSexo() {
		return sexo;
	}
	public void setSexo(AnimalSexo sexo) {
		this.sexo = sexo;
	}
	public AnimalIdade getIdade() {
		return idade;
	}
	public void setIdade(AnimalIdade idade) {
		this.idade = idade;
	}
	public AnimalPorte getPorte() {
		return porte;
	}
	public void setPorte(AnimalPorte porte) {
		this.porte = porte;
	}
	public Boolean getCastrado() {
		return castrado;
	}
	public void setCastrado(Boolean castrado) {
		this.castrado = castrado;
	}
	public Boolean getVacinado() {
		return vacinado;
	}
	public void setVacinado(Boolean vacinado) {
		this.vacinado = vacinado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Integer getId_animal() {
		return id_animal;
	}	
	public void validaSexoAnimal(String sexo) {
		switch(sexo){
			case "Macho":
				setSexo(AnimalSexo.MACHO);
				break;
			case "Fêmea":
				setSexo(AnimalSexo.FEMEA);
				break;
			default:
				setSexo(AnimalSexo.NAO_SEI);				
		}
	}
	public void validaIdadeAnimal(String idade) {
		switch(idade){
			case "Filhote":
				setIdade(AnimalIdade.FILHOTE);
				break;
			case "Adulto":
				setIdade(AnimalIdade.ADULTO);
				break;			
		}
	}	
	public void validaPorteAnimal(String porte) {
		switch(porte){
			case "Pequeno":
				setPorte(AnimalPorte.PEQUENO);
				break;
			case "Médio":
				setPorte(AnimalPorte.MEDIO);
				break;	
			case "Grande":
				setPorte(AnimalPorte.GRANDE);
				break;		
		}
	}	
}
