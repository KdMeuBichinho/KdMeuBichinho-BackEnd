package br.com.Kdmeubichinho.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;
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
	@Column(nullable = false)
	private Integer id_animal;
	@Column(nullable = false)
	private AnimalSexo sexo;
	@Column(nullable = false)
	private AnimalClassificacaoEtaria classificacaoEtaria;
	@Column(nullable = false)
	private AnimalPorte porte;
	@Column(nullable = false)
	private Boolean castrado;
	@Column(nullable = false)
	private Boolean vacinado;
	@Column(nullable = true)	
	private String nome;
	@Column(nullable = false)
	private String cep;
	@OneToOne()
	@JoinColumn(name = "fk_id_especie")
	private Especie especie;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_animal")
	@JsonIgnoreProperties("id_animal")
	private Set<Foto> fotos;
	
	public String getSexo() {
		return sexo.getDescricao();
	}
	public String getClassificacaoEtaria() {
		return classificacaoEtaria.getDescricao();
	}
	public String getPorte() {
		return porte.getDescricao();
	}
//	public AnimalClassificacaoEtaria getClassificacaoEtariaEnum() {
//		return classificacaoEtaria;
//	}
//	public AnimalPorte getPorteEnum() {
//		return porte;
//	}
//	public AnimalSexo getSexoEnum() {
//		return sexo;
//	}
	public Object getCastrado() {
		// TODO Auto-generated method stub
		return null;
	}
}
