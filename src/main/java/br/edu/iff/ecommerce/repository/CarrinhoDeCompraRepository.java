package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.CarrinhoDeCompra;

@Repository
public interface CarrinhoDeCompraRepository extends JpaRepository<CarrinhoDeCompra, Long> {

    @Query(value = "SELECT * FROM tb_carrinho_de_compra WHERE col_id_carrinho = ?1", nativeQuery = true)
    CarrinhoDeCompra buscarPorId(Long id);
}