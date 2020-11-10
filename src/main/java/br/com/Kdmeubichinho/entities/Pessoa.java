package br.com.Kdmeubichinho.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pessoa;
    private String nome;
    private String email;
    private String cep;
    private String rua;
    private String numero_residencial;
    private String celular;
    private String senha;
    @JsonIgnore
    private String autoridade;
}
