package br.com.Kdmeubichinho.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.Kdmeubichinho.entities.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Integer>{

}
