package br.edu.iff.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.ContaVendedor;

@Repository
public interface ContaVendedorRepository extends JpaRepository<ContaVendedor, Long> {

    @Query("SELECT cv FROM ContaVendedor cv WHERE cv.quantVendas >= :quantidade")
    List<ContaVendedor> encontrarPorQuantidadeDeVendas(int quantidade);

    @Query("SELECT cv FROM ContaVendedor cv WHERE cv.ptsAvaliacao >= :pontuacao")
    List<ContaVendedor> encontrarPorPontuacaoDeAvaliacao(int pontuacao);
    
    @Query(value = "SELECT * FROM tb_conta_vendedor WHERE col_id_conta = ?1", nativeQuery = true)
    ContaVendedor buscarPorId(Long id);

	ContaVendedor findByEmail(String email);
}
