package br.com.Kdmeubichinho.specification;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import br.com.Kdmeubichinho.entities.Anuncio;
import br.com.Kdmeubichinho.enums.AnimalClassificacaoEtaria;
import br.com.Kdmeubichinho.enums.AnimalPorte;
import br.com.Kdmeubichinho.enums.AnimalSexo;
import br.com.Kdmeubichinho.enums.AnuncioStatus;

public final class AnuncioSpecification {
	
//	public static Specification<Anuncio> AnimalSexoContains(String expression) {
//	    return (root, query, builder) -> builder.like(root.join("idAnimal").get("sexo"), contains(expression));
//	}
//	public static Specification<Anuncio> AnimalClassificacaoEtariaContains(String expression) {
//	    return (root, query, builder) -> builder.like(root.join("idAnimal").get("classificacaoEtaria"), contains(expression));
//	}
//	public static Specification<Anuncio> AnimalStatusContains(String expression) {
//	    return (root, query, builder) -> builder.like(root.get("status"), contains(expression));
//	}
//	public static Specification<Anuncio> StatusIn(String... values) {
//	    return (root, query, builder) -> builder.or(Arrays
//	        .stream(values)
//	        .map(value -> builder.equal(root.get("status"), value))
//	        .toArray(Predicate[]::new));
//	}
//	private static String contains(String expression) {
//	    return MessageFormat.format("%{0}%", expression);
//	}
	
    public static Specification<Anuncio> statusFilter(AnuncioStatus status) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }
    public static Specification<Anuncio> AnimalSexoFilter(AnimalSexo sexo) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("sexo"), sexo);
    }
    public static Specification<Anuncio> AnimalPorteFilter(AnimalPorte porte) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("porte"), porte);
    }
    public static Specification<Anuncio> AnimalClassificacaoEtariaFilter(AnimalClassificacaoEtaria classificacaoEtaria) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("classificacaoEtaria"), classificacaoEtaria);
    }
    public static Specification<Anuncio> AnimalCepFilter(String cep) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.join("idAnimal").get("cep"), contains(cep));
    }
	private static String contains(String expression) {
    return MessageFormat.format("{0}%", expression);
	}
    public static Specification<Anuncio> AnuncioCategoriaFilter(Integer idCategoria) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idCategoria").get("idCategoria"), idCategoria);
    }
    public static Specification<Anuncio> AnimalEspecieFilter(Integer idEspecie) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("especie"), idEspecie);
    }
    public static Specification<Anuncio> AnimalVacinadoFilter(Boolean vacinado) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("vacinado"), vacinado);
    }
    public static Specification<Anuncio> AnimalCastradoFilter(Boolean castrado) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("idAnimal").get("castrado"), castrado);
    }

}
