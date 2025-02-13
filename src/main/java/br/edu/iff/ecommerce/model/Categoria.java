package br.edu.iff.ecommerce.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_categoria")
    private Long idCategoria;

	@NotBlank
	@Column(name = "col_descricao", nullable = false, length = 125)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos;

    public Categoria(String descricao) {
        this.descricao = descricao;
        this.produtos = (Set<Produto>) produtos;
    }
    
    public Categoria() {
    	this.produtos = (Set<Produto>) produtos;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}