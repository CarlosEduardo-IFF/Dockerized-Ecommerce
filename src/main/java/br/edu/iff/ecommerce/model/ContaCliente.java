package br.edu.iff.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_contaCliente")
public class ContaCliente extends Conta {

	@Positive
	@Column(name = "col_creditos_cliente", nullable = false)
    private double creditosCliente;

    @Column(name = "col_quant_compras", nullable = false)
    private int quantCompras;

    @NotBlank
    @Column(name = "col_cpf", nullable = false, length = 14)
    private String cpf;
    
    @OneToOne
    @JoinColumn(name = "Col_fk_carrinho", nullable = false)
    private CarrinhoDeCompra carrinho;
    
    @Column(name = "col_dataNasc", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @ElementCollection
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Pedido> pedidos = new ArrayList<>();
    
    @ElementCollection
    @OneToMany(mappedBy = "contaCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Endereco> enderecos;

	public ContaCliente() {
	}

	public ContaCliente(String email, String senha, String nomeUsuario, String cpf, CarrinhoDeCompra carrinho, LocalDate data) {
		super(email, senha, nomeUsuario);
		this.quantCompras = 0;
		this.cpf = cpf;
		this.carrinho = carrinho;
		this.pedidos = new ArrayList<>();
		this.data = data;
		this.enderecos = new ArrayList<>();
	}

	public Collection<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Collection<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public double getCreditosCliente() {
        return creditosCliente;
    }

    public void setCreditosCliente(double creditosCliente) {
        this.creditosCliente = creditosCliente;
    }

     public int getQuantCompras() {
        return quantCompras;
    }

    public void setQuantCompras(int quantCompras) {
        this.quantCompras = quantCompras;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public CarrinhoDeCompra getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompra carrinho) {
		this.carrinho = carrinho;
	}
	
	public void addEndereco (Endereco endereco) {
		this.enderecos.add(endereco);
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Collection<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Collection<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	

}
