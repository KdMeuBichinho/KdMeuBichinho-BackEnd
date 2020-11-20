package br.com.Kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.dto.CategoriaRequestDTO;
import br.com.Kdmeubichinho.entities.Categoria;
import br.com.Kdmeubichinho.repositories.CategoriaRepository;

@RestController
@RequestMapping(path = "categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Iterable<Categoria> getCategoria(){
		return categoriaRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Categoria> getCategoriaById(@PathVariable Integer id){
		return categoriaRepository.findById(id);
	}
	@PutMapping()
	public CategoriaRequestDTO addCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
		categoriaRepository.save(categoriaRequestDTO.build());
		return categoriaRequestDTO;
	}
}