package br.edu.iff.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id_pedido")
    private long idPedido;

	  @Positive
    @Column(name = "col_valor_total", nullable = false)
    private double valorTotal;

	  @NotNull
    @Column(name = "col_data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Positive
    @Column(name = "col_quant_produtos", nullable = false)
    private int quantProdutos;
    
    @ManyToOne
    @JoinColumn(name = "col_fk_cliente", nullable = false)
    private ContaCliente cliente;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    private Collection<ItemPedido> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "col_fk_status", nullable = false)
    private Status status;

    public Pedido(double valorTotal, LocalDateTime dataHora, int quantProdutos, Status status) {
        this.valorTotal = valorTotal;
        this.dataHora = dataHora;
        this.quantProdutos = quantProdutos;
        this.status = status;
    }
    
    public Pedido() {
    	
    }
    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getQuantProdutos() {
        return quantProdutos;
    }

    public void setQuantProdutos(int quantProdutos) {
        this.quantProdutos = quantProdutos;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public ContaCliente getCliente() {
		return cliente;
	}

	public void setCliente(ContaCliente cliente) {
		this.cliente = cliente;
	}

	public Collection<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemPedido> itensPedido) {
		this.itens = itensPedido;
		
	}
    
    
    
    
}
