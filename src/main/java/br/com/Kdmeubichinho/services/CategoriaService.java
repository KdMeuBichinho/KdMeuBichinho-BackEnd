package br.com.Kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Kdmeubichinho.dto.CategoriaRequestDTO;
import br.com.Kdmeubichinho.entities.Categoria;
import br.com.Kdmeubichinho.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Iterable<Categoria> getAllCategory() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> getCategoryById(Integer id) {
		return categoriaRepository.findById(id);
	}

	public CategoriaRequestDTO addCategory(CategoriaRequestDTO categoryRequestDTO) {
		categoriaRepository.save(categoryRequestDTO.build());
		return categoryRequestDTO;
	}

	public void deleteCategory(Integer id) {
		categoriaRepository.deleteById(id);
	}

}
