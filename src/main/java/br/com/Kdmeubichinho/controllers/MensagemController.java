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

import br.com.Kdmeubichinho.entities.Mensagem;
import br.com.Kdmeubichinho.repositories.MensagemRepository;

@RestController
@RequestMapping(path = "/mensagem")
public class MensagemController {

	@Autowired
	private MensagemRepository mensagemRepository;
	
	@GetMapping()
	public Iterable<Mensagem> getMensagem(){
		return mensagemRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Mensagem> getById(@PathVariable Integer id){
		return mensagemRepository.findById(id);
	}
	
	@PostMapping()
	public Mensagem addMensagem(@RequestBody Mensagem mensagem) {
		mensagemRepository.save(mensagem);
		return mensagem;
	}
	
	@PutMapping("/{idMensagem}")
	public Mensagem updateMensagem(@PathVariable Integer idMensagem,@RequestBody Mensagem dadosMensagem) throws Exception{
		
		
		Mensagem meuMensagem = mensagemRepository.findById(idMensagem)
				.orElseThrow(()-> new IllegalAccessException());
		

		if(!dadosMensagem.getMensagem().isEmpty()) meuMensagem.setMensagem(dadosMensagem.getMensagem());
		
		
		mensagemRepository.save(meuMensagem);
		return meuMensagem;
	}
	
	@DeleteMapping("/{id}")
	public void deleteMensagem(@PathVariable Integer id) {
		mensagemRepository.deleteById(id);
	} 
}
