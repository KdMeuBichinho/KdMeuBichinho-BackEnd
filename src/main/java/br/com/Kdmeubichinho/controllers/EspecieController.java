package br.com.Kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.entities.Especie;
import br.com.Kdmeubichinho.repositories.EspecieRepository;

@RestController
@RequestMapping(path = "especie")
public class EspecieController {
	
	@Autowired
	EspecieRepository especieRepository;
	
	@GetMapping
	public Iterable<Especie> getEspecie(){
		return especieRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Especie> getEspecieById(@PathVariable Integer id){
		return especieRepository.findById(id);
	}
}
