package br.com.Kdmeubichinho.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.Kdmeubichinho.dto.CredenciaisDTO;
import br.com.Kdmeubichinho.dto.PessoaDTO;
import br.com.Kdmeubichinho.dto.TokenDTO;
import br.com.Kdmeubichinho.entities.Pessoa;
import br.com.Kdmeubichinho.exceptions.SenhaInvalidaException;
import br.com.Kdmeubichinho.repositories.PessoaRepository;

@Service
public class PessoaService implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtService jwtService;
    
    @Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Iterable<Pessoa> getAllPersons(){
		return pessoaRepository.findAll();
	}
	public Optional<Pessoa> getPersonById(Integer id){
		return pessoaRepository.findById(id);
	}
	public Pessoa getPersonByEmail(String email) {
		return pessoaRepository.findOneByEmail(email);
	}
	@Transactional
    public Pessoa savePerson(PessoaDTO pessoa){
        String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
        pessoa.setSenha(senhaCriptografada);
        return pessoaRepository.save(pessoa.build());
    }
    public ResponseEntity<TokenDTO> authenticatePerson(CredenciaisDTO credenciais){
        try{
        	System.out.println(credenciais);
            Pessoa pessoa = Pessoa.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            		authenticate(pessoa);
            String token = jwtService.gerarToken(pessoa);
            TokenDTO tokenDto =  new TokenDTO(pessoa.getEmail(), token);
            return new ResponseEntity<TokenDTO>(tokenDto, HttpStatus.OK);
            
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }	
	public UserDetails authenticate(Pessoa pessoa) {
		UserDetails user = loadUserByUsername(pessoa.getEmail());
		boolean senhasBatem = encoder.matches(pessoa.getSenha(), user.getPassword());
		
		if(senhasBatem) {
			return user;
		}
		
		throw new SenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));
	
		String[] roles = pessoa.isAdmin() ?
				new String[]{"ADMIN", "USER"} : new String[] {"USER"};
				
		return User
				.builder()
				.username(pessoa.getEmail())
				.password(pessoa.getSenha())
				.roles(roles)
				.build();
	}
	public Pessoa updatePerson(String emailPerson, PessoaDTO dataPerson) throws Exception{
		Pessoa myPerson = pessoaRepository.findByEmail(emailPerson)
				.orElseThrow(()-> new IllegalAccessException());
		
		if(!dataPerson.getNome().isEmpty()) myPerson.setNome(dataPerson.getNome());
		if(!dataPerson.getCelular().isEmpty()) myPerson.setCelular(dataPerson.getCelular());
		if(!dataPerson.getCep().isEmpty()) myPerson.setCep(dataPerson.getCep());
		if(!dataPerson.getLogradouro().isEmpty()) myPerson.setLogradouro(dataPerson.getLogradouro());
		if(!dataPerson.getComplemento().isEmpty()) myPerson.setComplemento(dataPerson.getComplemento());
		if(!dataPerson.getBairro().isEmpty()) myPerson.setBairro(dataPerson.getBairro());
		if(!dataPerson.getLocalidade().isEmpty()) myPerson.setLocalidade(dataPerson.getLocalidade());
		if(!dataPerson.getUf().isEmpty()) myPerson.setUf(dataPerson.getUf());
		if(!dataPerson.getIbge().isEmpty()) myPerson.setIbge(dataPerson.getIbge());
		if(!dataPerson.getDdd().isEmpty()) myPerson.setDdd(dataPerson.getDdd());
		if(!dataPerson.getNumeroResidencial().isEmpty()) myPerson.setNumeroResidencial(dataPerson.getNumeroResidencial());
		myPerson.setComplemento(dataPerson.getComplemento());
		
		pessoaRepository.save(myPerson);
		return myPerson;
	}
	public void deletePerson(Integer id) {
		pessoaRepository.deleteById(id);
	}
}
