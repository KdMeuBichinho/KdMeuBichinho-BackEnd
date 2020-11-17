package br.com.Kdmeubichinho.entities;

import java.util.Date;
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

import br.com.Kdmeubichinho.enums.AnuncioStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;



@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "anuncio", schema = "kdmeubichinho")
@Accessors(chain = true)
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anuncio")
	private Integer idAnuncio;
	@Column(name = "status_anuncio")
	private AnuncioStatus status;
	@Column(name = "data_criacao")
	private Date dataCriacao;
	@Column(name = "data_encerramento")
	private Date dataEncerramento;
	@OneToOne()
	@JoinColumn(name = "fk_id_pessoa")
	@JsonIgnoreProperties(value = {"senha"})
	private Pessoa idPessoa;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_animal")
	private Animal idAnimal;
	@OneToOne()
	@JoinColumn(name = "fk_id_categoria")
	private Categoria idCategoria;
	
	@JoinColumn(name = "fk_id_anuncio")
	@OneToMany()
	@JsonIgnoreProperties("id_anuncio")
	private Set<Mensagem> mensagens;
	
	public String getStatus() {
		return status.getDescricao();
	}

	public Anuncio(AnuncioStatus status, Date dataCriacao, Pessoa idPessoa, Animal idAnimal, Categoria idCategoria) {
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.idPessoa = idPessoa;
		this.idAnimal = idAnimal;
	}
}
