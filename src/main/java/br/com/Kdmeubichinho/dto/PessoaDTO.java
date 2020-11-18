package br.com.Kdmeubichinho.dto;

import br.com.Kdmeubichinho.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Integer idPessoa;
    private String nome;
    private String email;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String ddd;
    private String numeroResidencial;
    private String celular;
    private String senha;
    
    public Pessoa build() {
    	return new Pessoa(this.idPessoa, this.nome, this.email, this.cep, this.logradouro, this.complemento, this.bairro, this.localidade,
    			this.uf, this.ibge, this.ddd, this.numeroResidencial,	this.celular, this.senha);
    }
}
