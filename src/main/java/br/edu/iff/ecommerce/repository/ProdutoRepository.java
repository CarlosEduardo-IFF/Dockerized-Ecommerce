package br.edu.iff.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value = "SELECT * FROM tb_produto WHERE col_id_produto = ?1", nativeQuery = true)
	Produto buscarPorId(Long id);

	@Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE %:nome%")
	List<Produto> buscarPorNomeContendo(@Param("nome") String nome);

	@Query("SELECT p FROM Produto p WHERE p.preco < :preco")
	List<Produto> buscarPorPrecoMenorQue(@Param("preco") double preco);

    
}