package br.com.Kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.converters.AnimalClassificacaoEtariaConverter;
import br.com.Kdmeubichinho.converters.AnimalPorteConverter;
import br.com.Kdmeubichinho.converters.AnimalSexoConverter;
import br.com.Kdmeubichinho.entities.Animal;
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
	@GetMapping("/{id}")
	public Optional<Animal> getById(@PathVariable Integer id){
		return animalRepository.findById(id);
	}	
	@PostMapping("/cadastrar")
	public Animal addAnimal(@RequestBody Animal animal) {
		animalRepository.save(animal);
		return animal;
	}	
	@PutMapping("/{idAnimal}")
	public Animal updateAnimal(@PathVariable Integer idAnimal,@RequestBody Animal dadosAnimal) throws Exception{
		
		AnimalPorteConverter porteAnimalConverter = new AnimalPorteConverter();
		AnimalClassificacaoEtariaConverter classificacaoEtariaAnimalConverter = new AnimalClassificacaoEtariaConverter();
		AnimalSexoConverter sexoAnimalConverter = new AnimalSexoConverter();
		
		Animal myAnimal = animalRepository.findById(idAnimal)
				.orElseThrow(()-> new IllegalAccessException());
				
		myAnimal.setCastrado(dadosAnimal.getCastrado());
		myAnimal.setVacinado(dadosAnimal.getVacinado());
		if(!dadosAnimal.getCep().isEmpty()) myAnimal.setCep(dadosAnimal.getCep());
		myAnimal.setEspecie(dadosAnimal.getEspecie());
		if(!dadosAnimal.getNome().isEmpty()) myAnimal.setNome(dadosAnimal.getNome());
		if(!dadosAnimal.getPorte().isEmpty()) myAnimal.setPorte(porteAnimalConverter.convertToEntityAttribute(dadosAnimal.getPorte()));
		if(!dadosAnimal.getClassificacaoEtaria().isEmpty()) myAnimal.setClassificacaoEtaria(classificacaoEtariaAnimalConverter.convertToEntityAttribute(dadosAnimal.getClassificacaoEtaria()));;
		if(!dadosAnimal.getSexo().isEmpty()) myAnimal.setSexo(sexoAnimalConverter.convertToEntityAttribute(dadosAnimal.getSexo()));
		
		animalRepository.save(myAnimal);
		return myAnimal;
	}
	@DeleteMapping("/{id}")
	public void deleteAnimal(@PathVariable Integer id) {
		animalRepository.deleteById(id);
	}
	
}
