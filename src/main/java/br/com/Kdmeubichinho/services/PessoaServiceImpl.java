package br.com.Kdmeubichinho.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Kdmeubichinho.entities.Pessoa;
import br.com.Kdmeubichinho.exceptions.SenhaInvalidaException;
import br.com.Kdmeubichinho.repositories.PessoaRepository;

@Service
public class PessoaServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private PessoaRepository repository;
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		
		return repository.save(pessoa);
	}
	
	public UserDetails autenticar(Pessoa pessoa) {
		UserDetails user = loadUserByUsername(pessoa.getEmail());
		boolean senhasBatem = encoder.matches(pessoa.getSenha(), user.getPassword());
		
		if(senhasBatem) {
			return user;
		}
		
		throw new SenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));
	
		String[] roles = pessoa.getAutoridade().equals("ADMIN") ?
				new String[]{"ADMIN", "USER"} : new String[] {"USER"};
				
		return User
				.builder()
				.username(pessoa.getEmail())
				.password(pessoa.getSenha())
				.roles(roles)
				.build();
	}

}