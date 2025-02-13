package br.edu.iff.ecommerce.model;

import java.util.Collection;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_produto")
    private Long idProduto;

	 @NotBlank
	    @Size(max = 125)
    @Column(name = "col_nome", length = 125, nullable = false)
    private String nome;

	 @NotBlank
	    @Size(max = 225)
    @Column(name = "col_descricao", length = 225, nullable = false)
    private String descricao;

	 @Positive
    @Column(name = "col_preco", nullable = false)
    private double preco;

	 @Positive
    @Column(name = "col_quant_disponivel", nullable = false)
    private int quantDisponivel;

    @Column(name = "col_avaliacao")
    private int avaliacao;
    
    @Column(name = "col_imagem")
    private String imagem;
    
    @ManyToOne
    @JoinColumn(name = "col_id_conta_vendedor")
    private ContaVendedor contaVendedor;

    
    @ManyToMany
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "fk_produto"),
            inverseJoinColumns = @JoinColumn(name = "fk_categoria")
    )
    private Set<Categoria> categorias;

    public Produto(String nome, String descricao, double preco, int quantDisponivel, int avaliacao, Collection<Categoria> categorias) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantDisponivel = quantDisponivel;
        this.avaliacao = avaliacao;
        this.categorias = (Set<Categoria>) categorias;
    }
    
    public Produto(String nome, String descricao, double preco, int quantDisponivel, int avaliacao, Collection<Categoria> categorias, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantDisponivel = quantDisponivel;
        this.avaliacao = avaliacao;
        this.categorias = (Set<Categoria>) categorias;
        this.imagem = imagem;
    }
    
    public Produto() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() < 1 || nome.length() > 125) {
            throw new IllegalArgumentException("O nome deve ter entre 1 a 125 caracteres");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() < 1 || descricao.length() > 225) {
            throw new IllegalArgumentException("Descricao deve ter entre 1 e 225 caracteres");
        }
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }
        this.preco = preco;
    }

    public int getQuantDisponivel() {
        return quantDisponivel;
    }

    public void setQuantDisponivel(int quantDisponivel) {
        this.quantDisponivel = quantDisponivel;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public ContaVendedor getContaVendedor() {
		return contaVendedor;
	}

	public void setContaVendedor(ContaVendedor contaVendedor) {
		this.contaVendedor = contaVendedor;
	}
    
    
}