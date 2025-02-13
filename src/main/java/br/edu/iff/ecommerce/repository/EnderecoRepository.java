package br.edu.iff.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.iff.ecommerce.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT e FROM Endereco e WHERE e.cidade = :cidade")
	List<Endereco> buscarPorCidade(@Param("cidade") String cidade);

	@Query("SELECT e FROM Endereco e WHERE e.estado = :estado AND e.bairro = :bairro")
	List<Endereco> buscarPorEstadoEBairro(@Param("estado") String estado, @Param("bairro") String bairro);

}