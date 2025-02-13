package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	
	 @Query("SELECT s FROM Status s WHERE s.descricao = :descricao")
	    Status encontrarPorDescricao(String descricao);
}
