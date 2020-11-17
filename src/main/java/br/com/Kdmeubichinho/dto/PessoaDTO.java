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
    private String rua;
    private String numeroResidencial;
    private String celular;
    private String senha;
    
    public Pessoa build() {
    	return new Pessoa(this.idPessoa, this.nome, this.email, this.cep, this.rua, this.numeroResidencial,	this.celular, this.senha);
    }
}
