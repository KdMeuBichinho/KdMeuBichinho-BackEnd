package br.com.Kdmeubichinho.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@Column(name = "id_animal", nullable = false)
	private Integer idAnimal;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_foto")
	private Foto fotos;
	
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
}
