package br.edu.iff.ecommerce.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_contaVendedor")
public class ContaVendedor extends Conta {

	@Column(name = "col_quant_vendas", nullable = false)
    private int quantVendas;

    @Column(name = "col_pts_avaliacao", nullable = false)
    private int ptsAvaliacao;

    @NotBlank
    @Size(max = 255)
    @Column(name = "col_descricao", nullable = false, length = 255)
    private String descricao;

    @NotBlank
    @Column(name = "col_cnpj", nullable = false, length = 18)
    private String cnpj;
    
    @OneToMany(mappedBy = "contaVendedor")
    @ElementCollection
    private Collection<Produto> produtos;

	public ContaVendedor() {

	}

	public ContaVendedor(String email, String senha, String nomeUsuario, String descricao, String cnpj) {
		super(email, senha, nomeUsuario);
		this.descricao = descricao;
		this.cnpj = cnpj;
		this.quantVendas = 0;
		this.ptsAvaliacao = 0;
	
	}

	public int getQuantVendas() {
        return quantVendas;
    }

    public void setQuantVendas(int quantVendas) {
        this.quantVendas = quantVendas;
    }

    public int getPtsAvaliacao() {
        return ptsAvaliacao;
    }

    public void setPtsAvaliacao(int ptsAvaliacao) {
        this.ptsAvaliacao = ptsAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
    
	/*
	public Long getIdVendedor() {
		return this.getId();
	}
    */
}