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

import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.repositories.AnuncioRepository;

@RestController
@RequestMapping(path = "/anuncio")

public class AnuncioController {
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@GetMapping()
	public Iterable<Anuncio> getAnuncio(){
		return anuncioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Anuncio> getById(@PathVariable Integer id){
		return anuncioRepository.findById(id);
	}
	
	@PostMapping()
	public Anuncio addAnuncio(@RequestBody Anuncio anuncio) {
		anuncioRepository.save(anuncio);
		return anuncio;
	}
	
	@PutMapping("/{idAnuncio}")
	public Anuncio updateAnuncio(@PathVariable Integer idAnuncio,@RequestBody Anuncio dadosAnuncio) throws Exception{
		Anuncio meuAnuncio = anuncioRepository.findById(idAnuncio)
				.orElseThrow(()-> new IllegalAccessException());
		
		if(!dadosAnuncio.getTipo().isEmpty()) meuAnuncio.setTipo(dadosAnuncio.getTipo());
		if(!dadosAnuncio.getStatus().isEmpty()) meuAnuncio.setStatus(dadosAnuncio.getStatus());
		if(dadosAnuncio.getData_criacao() != null) meuAnuncio.setData_criacao(dadosAnuncio.getData_criacao());
		if(dadosAnuncio.getId_pessoa() != null) meuAnuncio.setId_pessoa(dadosAnuncio.getId_pessoa());
		if(dadosAnuncio.getId_animal() != null) meuAnuncio.setId_animal(dadosAnuncio.getId_animal());
		
		anuncioRepository.save(meuAnuncio);
		return meuAnuncio;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAnuncio(@PathVariable Integer id) {
		anuncioRepository.deleteById(id);
	}

}
