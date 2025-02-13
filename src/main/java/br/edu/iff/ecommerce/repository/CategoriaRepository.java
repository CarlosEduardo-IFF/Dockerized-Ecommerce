package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT * FROM tb_categoria WHERE col_descricao = ?1", nativeQuery = true)
    Categoria buscarPelaDescricao(String descricao);

    @Query(value = "SELECT * FROM tb_categoria WHERE col_id_categoria = ?1", nativeQuery = true)
    Categoria buscarPeloId(Long id);

}