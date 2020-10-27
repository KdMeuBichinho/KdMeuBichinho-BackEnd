package br.com.Kdmeubichinho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.models.entities.Animal;
import br.com.Kdmeubichinho.repositories.AnimalRepository;

@RestController
@RequestMapping(path = "animal")
public class AnimalController {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@GetMapping()
	public Iterable<Animal> getAnimais(){
		return animalRepository.findAll();
	}
	
	@PostMapping("/cadastrar")
	public Animal addAnimal(@RequestBody Animal animal) {
		animalRepository.save(animal);
		return animal;
		
	}
	
}
