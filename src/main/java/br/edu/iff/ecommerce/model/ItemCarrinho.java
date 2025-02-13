package br.edu.iff.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itemCarrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_itemCarrinho")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "col_fk_carrinho")
    private CarrinhoDeCompra carrinhoDeCompra;

    @ManyToOne
    @JoinColumn(name = "col_fk_produto")
    private Produto produto;

    @Column(name = "col_quantidade")
    private int quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarrinhoDeCompra getCarrinhoDeCompra() {
		return carrinhoDeCompra;
	}

	public void setCarrinhoDeCompra(CarrinhoDeCompra carrinhoDeCompra) {
		this.carrinhoDeCompra = carrinhoDeCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
