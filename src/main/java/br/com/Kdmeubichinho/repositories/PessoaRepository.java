package br.com.Kdmeubichinho.repositories;


import org.springframework.data.repository.CrudRepository;

import br.com.Kdmeubichinho.entities.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer>{
	Pessoa findOneByEmail(String email);
}
