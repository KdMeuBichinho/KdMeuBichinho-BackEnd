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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.Kdmeubichinho.entities.Pessoa;
import br.com.Kdmeubichinho.repositories.PessoaRepository;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping()
	public Iterable<Pessoa> getPessoas(){
		return pessoaRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Pessoa> getById(@PathVariable Integer id){
		return pessoaRepository.findById(id);
	}
	@GetMapping("/email")
	public Pessoa getByEmail(@RequestParam String email) {
		return pessoaRepository.findOneByEmail(email);
	}
	
	@PostMapping()
	public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}
	@PutMapping("/{idPessoa}")
	public Pessoa updatePessoa(@PathVariable Integer idPessoa,@RequestBody Pessoa dadosPessoa) throws Exception{
		Pessoa myPessoa = pessoaRepository.findById(idPessoa)
				.orElseThrow(()-> new IllegalAccessException());
		
		if(!dadosPessoa.getEmail().isEmpty()) myPessoa.setEmail(dadosPessoa.getEmail());
		if(!dadosPessoa.getNome().isEmpty()) myPessoa.setNome(dadosPessoa.getNome());
		if(!dadosPessoa.getCelular().isEmpty()) myPessoa.setCelular(dadosPessoa.getCelular());
		if(!dadosPessoa.getCep().isEmpty()) myPessoa.setCep(dadosPessoa.getCep());
		if(!dadosPessoa.getRua().isEmpty()) myPessoa.setRua(dadosPessoa.getRua());
		if(!dadosPessoa.getNumero_residencial().isEmpty()) myPessoa.setNumero_residencial(dadosPessoa.getNumero_residencial());
		if(!dadosPessoa.getSenha().isEmpty()) myPessoa.setSenha(dadosPessoa.getSenha());
		
		pessoaRepository.save(myPessoa);
		return myPessoa;
	}
	@DeleteMapping("/{id}")
	public void deletePessoa(@PathVariable Integer id) {
		pessoaRepository.deleteById(id);
	}

}
