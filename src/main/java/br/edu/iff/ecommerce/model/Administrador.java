package br.edu.iff.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contaAdmin")
public class Administrador extends Conta {

    @Column(name = "col_cpf", nullable = false, length = 14)
    private String cpf;

	public Administrador() {
	}


	public Administrador(String email, String senha, String nomeUsuario, String cpf) {
		super(email, senha, nomeUsuario);
		this.cpf = cpf;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

}

