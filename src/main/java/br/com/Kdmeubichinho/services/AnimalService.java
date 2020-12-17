package br.com.Kdmeubichinho.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Kdmeubichinho.entities.Animal;
import br.com.Kdmeubichinho.repositories.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;
	
	public Iterable<Animal> getAllAnimals(){
		return animalRepository.findAll();
	}

}
