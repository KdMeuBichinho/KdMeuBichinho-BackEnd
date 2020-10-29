package br.com.Kdmeubichinho.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_anuncio;
	private String tipo;
	private String status;
	private Date data_criacao;
	@OneToOne
	private Pessoa id_pessoa;
	@OneToOne
	private Animal id_animal;
	
	public Anuncio() {};
	
	public Integer getId_anuncio() {
		return id_anuncio;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getData_criacao() {
		return data_criacao;
	}
	
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	
	public Pessoa getId_pessoa() {
		return id_pessoa;
	}
	
	public void setId_pessoa(Pessoa id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	
	public Animal getId_animal() {
		return id_animal;
	}
	
	public void setId_animal(Animal id_animal) {
		this.id_animal = id_animal;
	}
	
}
