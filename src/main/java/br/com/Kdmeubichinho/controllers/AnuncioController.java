package br.com.Kdmeubichinho.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.converters.AnuncioStatusConverter;
import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;
import br.com.Kdmeubichinho.repositories.AnuncioRepository;

@RestController
@RequestMapping(path = "/anuncio")

public class AnuncioController {
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@GetMapping()
	public Iterable<Anuncio> getAnuncio(Pageable pageable){
		return anuncioRepository.findAll(pageable);
	}
	@GetMapping("/{id}")
	public Optional<Anuncio> getById(@PathVariable Integer id){
		return anuncioRepository.findById(id);
	}
	@GetMapping("/sexo")
	public List<Anuncio> getBySexo(@RequestParam AnimalSexo sexo) {
		return anuncioRepository.findByIdAnimal_Sexo(sexo);
	}
	@GetMapping("/especie")
	public List<Anuncio> getByEspecie(@RequestParam Integer id) {
		return anuncioRepository.findByIdAnimal_Especie_IdEspecie(id);
	}
	@GetMapping("/categoria")
	public List<Anuncio> getByCategoria(@RequestParam Integer id) {
		return anuncioRepository.findByIdCategoria_IdCategoria(id);
	}
	@GetMapping("/classificacaoEtaria")
	public List<Anuncio> getByClassificacaoEtaria(@RequestParam AnimalClassificacaoEtaria classificacaoEtaria) {
		return anuncioRepository.findByIdAnimal_ClassificacaoEtaria(classificacaoEtaria);
	}
	@GetMapping("/porte")
	public List<Anuncio> getByPorte(@RequestParam AnimalPorte porte) {
		return anuncioRepository.findByIdAnimal_Porte(porte);
	}
	@PostMapping()
	public Anuncio addAnuncio(@RequestBody Anuncio anuncio) {
		anuncioRepository.save(anuncio);
		return anuncio;
	}
	@PutMapping("/{idAnuncio}")
	public Anuncio updateAnuncio(@PathVariable Integer idAnuncio,@RequestBody Anuncio dadosAnuncio) throws Exception{
		
		AnuncioStatusConverter statusConverter = new AnuncioStatusConverter();
		Anuncio meuAnuncio = anuncioRepository.findById(idAnuncio)
				.orElseThrow(()-> new IllegalAccessException());
		

		if(!dadosAnuncio.getStatus().isEmpty()) meuAnuncio.setStatus(statusConverter.convertToEntityAttribute(dadosAnuncio.getStatus()));
		if(dadosAnuncio.getData_criacao() != null) meuAnuncio.setData_criacao(dadosAnuncio.getData_criacao());
		if(dadosAnuncio.getData_encerramento() != null) meuAnuncio.setData_encerramento(dadosAnuncio.getData_encerramento());		
		if(dadosAnuncio.getId_pessoa() != null) meuAnuncio.setId_pessoa(dadosAnuncio.getId_pessoa());
		if(dadosAnuncio.getIdAnimal() != null) meuAnuncio.setIdAnimal(dadosAnuncio.getIdAnimal());
		if(dadosAnuncio.getIdCategoria() != null) meuAnuncio.setIdCategoria(dadosAnuncio.getIdCategoria());
		
		anuncioRepository.save(meuAnuncio);
		return meuAnuncio;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAnuncio(@PathVariable Integer id) {
		anuncioRepository.deleteById(id);
	}

}
