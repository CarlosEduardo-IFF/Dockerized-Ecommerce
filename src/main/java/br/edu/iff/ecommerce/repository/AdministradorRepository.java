package br.edu.iff.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ecommerce.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	Administrador findByEmail(String email);

}
