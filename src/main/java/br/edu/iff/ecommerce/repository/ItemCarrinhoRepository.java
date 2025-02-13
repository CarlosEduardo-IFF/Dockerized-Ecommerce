package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.ItemCarrinho;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    @Query(value = "SELECT * FROM tb_item_carrinho WHERE col_id_item_carrinho = ?1", nativeQuery = true)
    ItemCarrinho buscarPorId(Long id);
}