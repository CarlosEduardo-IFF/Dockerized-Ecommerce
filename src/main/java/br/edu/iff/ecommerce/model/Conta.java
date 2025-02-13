package br.edu.iff.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Conta{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_conta")
    private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "col_nome", length = 60)
	private String nomeUsuario;

	@NotBlank
	@Email
    @Column(name = "col_email", nullable = false, length = 255)
    private String email;

	  @NotBlank
    @Column(name = "col_senha", nullable = false)
    private String senha;

    public Conta() {

	}

    public Conta(String email, String senha, String nomeUsuario) {
		this.email = email;
		this.senha = senha;
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

    
}