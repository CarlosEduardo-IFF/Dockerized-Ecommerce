package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.ContaCliente;

import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<ContaCliente, Long> {

    @Query(value = "SELECT * FROM tb_conta_cliente WHERE col_email = ?1", nativeQuery = true)
    ContaCliente buscarPorEmail(String email);

    @Query(value = "SELECT * FROM tb_conta_cliente WHERE col_cpf = ?1", nativeQuery = true)
    ContaCliente buscarPorCpf(String cpf);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_conta_cliente WHERE col_id_conta = ?1", nativeQuery = true)
    void removerPorId(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_conta_cliente SET col_creditos_cliente = ?2 WHERE col_id_conta = ?1", nativeQuery = true)
    void alterarCredito(Long id, double novoCredito);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_conta_cliente SET col_nome = ?2 WHERE col_id_conta = ?1", nativeQuery = true)
    void alterarNome(Long id, String novoNome);

	@Query(value = "SELECT * FROM tb_conta_cliente WHERE col_id_conta = ?1", nativeQuery = true)
    ContaCliente buscarPorId(Long id);

}
