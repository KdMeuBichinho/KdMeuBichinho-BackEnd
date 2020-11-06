package br.com.Kdmeubichinho.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_foto;
	private String caminho;
	@ManyToOne
	@JoinColumn(name = "fk_id_animal")
	@JsonIgnoreProperties("fotos")
	private Animal id_animal;
	
}
