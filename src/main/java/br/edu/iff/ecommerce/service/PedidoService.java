package br.edu.iff.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.CarrinhoDeCompra;
import br.edu.iff.ecommerce.model.ContaCliente;
import br.edu.iff.ecommerce.model.ItemCarrinho;
import br.edu.iff.ecommerce.model.ItemPedido;
import br.edu.iff.ecommerce.model.Pedido;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.model.Status;
import br.edu.iff.ecommerce.repository.ClienteRepository;
import br.edu.iff.ecommerce.repository.PedidoRepository;
import br.edu.iff.ecommerce.repository.ProdutoRepository;
import br.edu.iff.ecommerce.repository.StatusRepository;

@Service
public class PedidoService {

	@Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private StatusRepository statusRepository;

    public Long criarPedido(Long clienteId) throws Exception, Exception,
            Exception, Exception {
    	
        ContaCliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente com ID " + clienteId + " não encontrado"));

        Status status = statusRepository.findById((long) 1)
                .orElseThrow(() -> new Exception("Status com ID 1 não encontrado"));
        
        CarrinhoDeCompra carrinho = cliente.getCarrinho();
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            throw new Exception("O carrinho de compras do cliente está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataHora(LocalDateTime.now());

        ArrayList<ItemPedido> itensPedido = new ArrayList<>();
        double valorTotalPedido = 0.0;
        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            Produto produto = itemCarrinho.getProduto();
            int quantidadePedido = itemCarrinho.getQuantidade();

            Produto produtoAtualizado = produtoRepository.findById(produto.getIdProduto())
                    .orElseThrow(() -> new Exception(
                            "Produto com ID " + produto.getIdProduto() + " não encontrado"));

            int quantidadeDisponivel = produtoAtualizado.getQuantDisponivel();
            if (quantidadeDisponivel < quantidadePedido) {
                throw new Exception(
                        "Quantidade insuficiente para o produto: " + produtoAtualizado.getNome());
            }

            produtoAtualizado.setQuantDisponivel(quantidadeDisponivel - quantidadePedido);
            produtoRepository.save(produtoAtualizado);

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produtoAtualizado);
            itemPedido.setQuantidade(quantidadePedido);

            itensPedido.add(itemPedido);
            valorTotalPedido += produtoAtualizado.getPreco() * quantidadePedido;
        }

        pedido.setItens(itensPedido);
        pedido.setStatus(status);
        pedido.setValorTotal(valorTotalPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return pedidoSalvo.getIdPedido();
    }

    public void cancelarPedido(Long id) throws Exception {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new Exception("Pedido com ID " + id + " não encontrado"));

        pedidoRepository.delete(pedido);
    }
    
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.listarTodosPedidos();
    }
    
    public void atualizarStatus(Long pedidoId, Long novoStatusId) throws Exception, Exception {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new Exception("Pedido com ID " + pedidoId + " não encontrado"));

        Status novoStatus = statusRepository.findById(novoStatusId)
                .orElseThrow(() -> new Exception("Status com ID " + novoStatusId + " não encontrado"));

        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }
}