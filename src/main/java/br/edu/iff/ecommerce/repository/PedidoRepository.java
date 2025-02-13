package br.edu.iff.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p JOIN FETCH p.itens WHERE p.idPedido = :pedidoId")
    Pedido buscarPedidoPorId(@Param("pedidoId") Long pedidoId) throws Exception;

    @Modifying
    @Query("DELETE FROM Pedido p WHERE p.idPedido = :pedidoId")
    void removerPedidoPorId(@Param("pedidoId") Long pedidoId) throws Exception;
    
    @Query("SELECT p FROM Pedido p")
    List<Pedido> listarTodosPedidos();

}