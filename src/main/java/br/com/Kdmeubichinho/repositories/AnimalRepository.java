package br.com.Kdmeubichinho.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.Kdmeubichinho.models.entities.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Integer>{

}
