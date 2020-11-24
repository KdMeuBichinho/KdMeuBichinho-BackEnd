package br.com.Kdmeubichinho.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.Kdmeubichinho.entities.Foto;
import br.com.Kdmeubichinho.repositories.FotoRepository;
import br.com.Kdmeubichinho.util.FileUploadUtil;

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
	@CrossOrigin
	@PostMapping()
	public String saveImg(@RequestParam("image") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "target/classes/static";
		
		Date date = new Date();
		String filePrefix = date.getTime() + "-";
		fileName = filePrefix + fileName;
		
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, file);			
		} catch(IOException e) {
			return ("Não foi possível salvar o arquivo: " + fileName);
		}
//		return uploadDir + "/" + fileName;
		return fileName;
	}
	
//	@PostMapping()
//	public Foto addFoto(@RequestBody Foto foto) {
//		fotoRepository.save(foto);
//		return foto;
//	}
	
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
