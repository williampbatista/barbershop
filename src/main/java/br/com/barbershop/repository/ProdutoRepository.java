package br.com.barbershop.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.barbershop.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepositoryImplementation<Produto, Long>{

}
