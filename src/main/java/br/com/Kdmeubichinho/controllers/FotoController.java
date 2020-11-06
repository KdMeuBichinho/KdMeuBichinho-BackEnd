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



import br.com.Kdmeubichinho.entities.Foto;
import br.com.Kdmeubichinho.repositories.FotoRepository;

@RestController
@RequestMapping(path = "foto")
public class FotoController {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@GetMapping()
	public Iterable<Foto> getFoto(){
		return fotoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Foto> getById(@PathVariable Integer id){
		return fotoRepository.findById(id);
	}
	
	@PostMapping()
	public Foto addFoto(@RequestBody Foto anuncio) {
		fotoRepository.save(anuncio);
		return anuncio;
	}
	
	@PutMapping("/{idFoto}")
	public Foto updateFoto(@PathVariable Integer idFoto,@RequestBody Foto dadosFoto) throws Exception{
		
		
		Foto meuFoto = fotoRepository.findById(idFoto)
				.orElseThrow(()-> new IllegalAccessException());
		

		if(!dadosFoto.getCaminho().isEmpty()) meuFoto.setCaminho(dadosFoto.getCaminho());
		
		
		fotoRepository.save(meuFoto);
		return meuFoto;
	}
	
	@DeleteMapping("/{id}")
	public void deleteFoto(@PathVariable Integer id) {
		fotoRepository.deleteById(id);
	}

}
