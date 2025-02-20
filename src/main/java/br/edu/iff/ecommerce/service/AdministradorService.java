package br.edu.iff.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.Administrador;
import br.edu.iff.ecommerce.repository.AdministradorRepository;

@Service
public class AdministradorService {

	
	 @Autowired
	 private AdministradorRepository administradorRepository;

	public Administrador findByEmail(String email) {
		 return administradorRepository.findByEmail(email);
	}

	public Administrador criarAdministrador(String email, String senha, String nomeUsuario, String cpf){

		Administrador admin = new Administrador(email, senha, nomeUsuario, cpf);

		return administradorRepository.save(admin);

	}

}
