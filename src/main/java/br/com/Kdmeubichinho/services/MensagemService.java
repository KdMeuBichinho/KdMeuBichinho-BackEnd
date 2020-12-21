package br.com.Kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Kdmeubichinho.entities.Mensagem;
import br.com.Kdmeubichinho.entities.Pessoa;
import br.com.Kdmeubichinho.repositories.MensagemRepository;
import br.com.Kdmeubichinho.repositories.PessoaRepository;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Iterable<Mensagem> getAllMessages(){
		return mensagemRepository.findAll();
	}
	public Optional<Mensagem> getMessageById(Integer id){
		return mensagemRepository.findById(id);
	}
	public Mensagem addMessage(Mensagem message){
		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(message.getIdPessoa().getEmail());
		if(pessoa.isPresent()) {
			Integer pessoaId = pessoa.get().getIdPessoa();
			message.getIdPessoa().setIdPessoa(pessoaId);
		}		
		mensagemRepository.save(message);
		return message;
	}
	public Mensagem updateMessage(Integer idMessage, Mensagem dataMessage) throws Exception{
		Mensagem messageToUpdate = mensagemRepository.findById(idMessage)
				.orElseThrow(()-> new IllegalAccessException());
		
		if(!dataMessage.getMensagem().isEmpty()) messageToUpdate.setMensagem(dataMessage.getMensagem());
		
		mensagemRepository.save(messageToUpdate);
		return messageToUpdate;
	}
	public void deleteMessage(Integer id) {
		mensagemRepository.deleteById(id);
	} 

}
