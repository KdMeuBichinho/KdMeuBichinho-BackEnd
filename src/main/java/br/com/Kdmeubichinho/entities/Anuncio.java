package br.com.Kdmeubichinho.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.Kdmeubichinho.enums.AnuncioStatus;
import lombok.Getter;
import lombok.Setter;



@Entity
@Setter
@Getter
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_anuncio;
	@Column(name = "status_anuncio")
	private AnuncioStatus status;
	private Date data_criacao;
	private Date data_encerramento;
	@OneToOne()
	@JoinColumn(name = "fk_id_pessoa")
	private Pessoa id_pessoa;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_animal")
	private Animal id_animal;
	@OneToOne()
	@JoinColumn(name = "fk_id_categoria")
	private Categoria id_categoria;
	
	public String getStatus() {
		return status.getDescricao();
	}
}
