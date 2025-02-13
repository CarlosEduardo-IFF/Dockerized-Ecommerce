package br.edu.iff.ecommerce.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.CarrinhoDeCompra;
import br.edu.iff.ecommerce.model.ItemCarrinho;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.repository.CarrinhoDeCompraRepository;
import br.edu.iff.ecommerce.repository.ItemCarrinhoRepository;
import br.edu.iff.ecommerce.repository.ProdutoRepository;


@Service
public class CarrinhoDeCompraService {

	@Autowired
    private final CarrinhoDeCompraRepository carrinhoRepository;
	@Autowired
    private final ItemCarrinhoRepository itemCarrinhoRepository;
	@Autowired
    private final ProdutoRepository produtoRepository;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoRepository,
                                   ItemCarrinhoRepository itemCarrinhoRepository,
                                   ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public String adicionarItem(Long carrinhoId, Long produtoId, int quantidade) {
        CarrinhoDeCompra carrinho = carrinhoRepository.buscarPorId(carrinhoId);
        if (carrinho == null) {
            return "Carrinho não encontrado com o ID fornecido";
        }

        Produto produto = produtoRepository.buscarPorId(produtoId);
        if (produto == null) {
            return "Produto não encontrado com o ID fornecido";
        }

        if (quantidade <= 0 || quantidade > produto.getQuantDisponivel()) {
            return "Quantidade inválida ou excede a quantidade disponível do produto";
        }

        List<ItemCarrinho> itens = (List<ItemCarrinho>) carrinho.getListaDeProdutos();
        ItemCarrinho itemExistente = null;

        for (ItemCarrinho item : itens) {
            if (item.getProduto().getIdProduto().equals(produtoId)) {
                itemExistente = item;
                break;
            }
        }

        if (itemExistente != null) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
            itemCarrinhoRepository.save(itemExistente);
        } else {
            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);
            novoItem.setCarrinhoDeCompra(carrinho);
            itens.add(novoItem);
            itemCarrinhoRepository.save(novoItem);
        }
        carrinho.setQuantProdutos(carrinho.getQuantProdutos() + quantidade);
        carrinhoRepository.save(carrinho);
        

        return "Item adicionado ao carrinho";
    }

    public String removerItem(Long carrinhoId, Long itemId) {
        CarrinhoDeCompra carrinho = carrinhoRepository.buscarPorId(carrinhoId);
        if (carrinho == null) {
            return "Carrinho não encontrado com o ID fornecido";
        }

        ItemCarrinho itemParaRemover = itemCarrinhoRepository.buscarPorId(itemId);
        if (itemParaRemover == null) {
            return "Item não encontrado com o ID fornecido";
        }

        carrinho.setQuantProdutos(carrinho.getQuantProdutos() - itemParaRemover.getQuantidade());
        carrinho.getListaDeProdutos().remove(itemParaRemover);
        carrinhoRepository.save(carrinho);
        
        itemCarrinhoRepository.delete(itemParaRemover);

        return "Item removido do carrinho";
    }

    public String esvaziarCarrinho(Long carrinhoId) {
        CarrinhoDeCompra carrinho = carrinhoRepository.buscarPorId(carrinhoId);
        if (carrinho == null) {
            return "Carrinho não encontrado com o ID fornecido";
        }

        List<ItemCarrinho> itens = (List<ItemCarrinho>) carrinho.getListaDeProdutos();
        itens.clear();
        carrinho.setQuantProdutos(0);
        carrinhoRepository.save(carrinho);

        return "Carrinho esvaziado";
    }
    
    public CarrinhoDeCompra obterCarrinhoPorId(Long id) {
    	return carrinhoRepository.buscarPorId(id);
    }
    
    public String editarQuantidade(Long carrinhoId, ItemCarrinho item, Long produtoId){
        CarrinhoDeCompra carrinho = carrinhoRepository.buscarPorId(carrinhoId);
        if (carrinho == null) {
            return "Carrinho não encontrado com o ID fornecido";
        }

        ItemCarrinho itemParaEditar = itemCarrinhoRepository.buscarPorId(produtoId);
        if (itemParaEditar == null) {
            return "Item não encontrado com o ID fornecido";
        }

        if (item.getQuantidade() <= 0) {
            return "Quantidade inválida";
        }

        if (item.getQuantidade() > itemParaEditar.getProduto().getQuantDisponivel()) {
            return "Quantidade excede a quantidade disponível do produto";
        }

        itemParaEditar.setQuantidade(item.getQuantidade());
        itemCarrinhoRepository.save(itemParaEditar);

        int quantidadeTotal = 0;
        List<ItemCarrinho> itens = (List<ItemCarrinho>) carrinho.getListaDeProdutos();
        for (ItemCarrinho item2 : itens) {
            quantidadeTotal += item2.getQuantidade();
        }
        carrinho.setQuantProdutos(quantidadeTotal);
        carrinhoRepository.save(carrinho);

        return "Quantidade do item atualizada com sucesso";
    }
    
    public ItemCarrinho obterItemPorId(Long id) {
    	return itemCarrinhoRepository.buscarPorId(id);
    }
}