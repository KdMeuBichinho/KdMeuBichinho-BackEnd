package br.com.Kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.Kdmeubichinho.dto.CredenciaisDTO;
import br.com.Kdmeubichinho.dto.PessoaDTO;
import br.com.Kdmeubichinho.dto.TokenDTO;
import br.com.Kdmeubichinho.entities.Pessoa;
import br.com.Kdmeubichinho.exceptions.SenhaInvalidaException;
import br.com.Kdmeubichinho.repositories.PessoaRepository;
import br.com.Kdmeubichinho.services.JwtService;
import br.com.Kdmeubichinho.services.PessoaServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/pessoa")
@RequiredArgsConstructor
public class PessoaController {
	
	private final PessoaServiceImpl pessoaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
	
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
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar( @RequestBody PessoaDTO pessoa ){
        String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
        pessoa.setSenha(senhaCriptografada);
        return pessoaService.salvar(pessoa.build());
    }
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
        	System.out.println(credenciais);
            Pessoa pessoa = Pessoa.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            UserDetails pessoaAutenticado = pessoaService.autenticar(pessoa);
            String token = jwtService.gerarToken(pessoa);
            return new TokenDTO(pessoa.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
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
		if(!dadosPessoa.getNumeroResidencial().isEmpty()) myPessoa.setNumeroResidencial(dadosPessoa.getNumeroResidencial());
		if(!dadosPessoa.getSenha().isEmpty()) myPessoa.setSenha(dadosPessoa.getSenha());
		
		pessoaRepository.save(myPessoa);
		return myPessoa;
	}
	@DeleteMapping("/{id}")
	public void deletePessoa(@PathVariable Integer id) {
		pessoaRepository.deleteById(id);
	}

}
