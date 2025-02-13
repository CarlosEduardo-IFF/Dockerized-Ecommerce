package br.edu.iff.ecommerce.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_carrinhoDeCompra")
public class CarrinhoDeCompra {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_carrinho")
    private Long idCarrinho;

	@NotNull
    @Positive
    @Column(name = "col_quant_produtos", nullable = false)
    private int quantProdutos;

    @OneToMany(mappedBy = "carrinhoDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    private Collection<ItemCarrinho> itens = new ArrayList<>();

    public CarrinhoDeCompra() {
    }

    public CarrinhoDeCompra(int quantProdutos) {
        this.quantProdutos = quantProdutos;
        this.itens = new ArrayList<>();
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public int getQuantProdutos() {
        return quantProdutos;
    }

    public void setQuantProdutos(int quantProdutos) {
        this.quantProdutos = quantProdutos;
    }

    public Collection<ItemCarrinho> getListaDeProdutos() {
        return itens;
    }

    public void setListaDeProdutos(Collection<ItemCarrinho> listaDeProdutos) {
        this.itens = listaDeProdutos;
    }

	public Collection<ItemCarrinho> getItens() {
		
		return itens;
	}
}