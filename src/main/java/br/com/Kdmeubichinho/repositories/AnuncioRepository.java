package br.com.Kdmeubichinho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.entities.Categoria;
import br.com.Kdmeubichinho.entities.Especie;
import br.com.Kdmeubichinho.enums.AnimalSexo;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{
//	@Query(value = "select c from Cliente c where nome like %:nome%")
//	List<Cliente> encontrarNomeParecido(String nome);

	List<Anuncio> findByIdAnimal_Sexo(AnimalSexo sexo);
	List<Anuncio> findByEspecie_Especie(Especie especie);
	List<Anuncio> findByIdCategoria_Categoria(Categoria categoria);
}
