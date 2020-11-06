package br.com.Kdmeubichinho.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_mensagem;
	
	private Date data_mensagem;
	
	private String mensagem;
	
	@JoinColumn(name = "fk_id_pessoa")
	@OneToOne
	private Pessoa id_pessoa;
	
	@JoinColumn(name = "fk_id_anuncio")
	@OneToOne
	@JsonIgnoreProperties("mensagem")
	private Anuncio id_anuncio;
}