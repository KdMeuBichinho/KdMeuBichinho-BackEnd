package br.com.Kdmeubichinho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{
	List<Anuncio> findByIdAnimal_Sexo(AnimalSexo sexo);
	List<Anuncio> findByIdAnimal_Especie_IdEspecie(Integer id);
	List<Anuncio> findByIdCategoria_IdCategoria(Integer id);
	List<Anuncio> findByIdAnimal_ClassificacaoEtaria(AnimalClassificacaoEtaria classificacaoEtaria);
	List<Anuncio> findByIdAnimal_Porte(AnimalPorte porte);
}
