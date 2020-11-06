package br.com.Kdmeubichinho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Kdmeubichinho.entities.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer>{

}
