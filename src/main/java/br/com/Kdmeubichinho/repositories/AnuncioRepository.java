package br.com.Kdmeubichinho.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{
	Page<Anuncio> findByIdAnimal_Sexo(AnimalSexo sexo, Pageable pageable);
	Page<Anuncio> findByIdAnimal_Especie_IdEspecie(Integer id, Pageable pageable);
	Page<Anuncio> findByIdCategoria_IdCategoria(Integer id, Pageable pageable);
	Page<Anuncio> findByIdAnimal_ClassificacaoEtaria(AnimalClassificacaoEtaria classificacaoEtaria, Pageable pageable);
	Page<Anuncio> findByIdAnimal_Porte(AnimalPorte porte, Pageable pageable);
	List<Anuncio> findByIdAnimal_SexoAndIdAnimal_Porte(AnimalSexo sexo, AnimalPorte porte);
}
