package br.edu.iff.ecommerce.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.Categoria;
import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.repository.CategoriaRepository;
import br.edu.iff.ecommerce.repository.ContaVendedorRepository;
import br.edu.iff.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private static String caminhoImagens = "/app/uploads/";

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ContaVendedorRepository vendedorRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ContaVendedorRepository vendedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public Produto criarProduto(Produto produto, Long idVendedor) {
    	ContaVendedor vendedor = vendedorRepository.findById(idVendedor)
                .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));
    	produto.setContaVendedor(vendedor);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produto) {
    	Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrada com o ID: " + id));
        
        existingProduto.setDescricao(produto.getDescricao());
        existingProduto.setQuantDisponivel(produto.getQuantDisponivel());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setNome(produto.getNome());
        
       return produtoRepository.save(existingProduto);
    }

    public void deletarProduto(Long id) {
    	   Produto produto = produtoRepository.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    	   
    	   String imagemAntiga = produto.getImagem();
    	   File arquivo = new File(caminhoImagens + imagemAntiga);
    	   arquivo.delete();
    	
        produtoRepository.deleteById(id);
    }

    public Produto adicionarCategoriaAoProduto(Long produtoId, Long categoriaId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        produto.getCategorias().add(categoria);
        return produtoRepository.save(produto);
    }
    
    public Produto alterarImagem(Long produtoId, String novaImaagem) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        String imagemAntiga = produto.getImagem();
 	   File arquivo = new File(caminhoImagens + imagemAntiga);
 	   arquivo.delete();
 	   
        produto.setImagem(novaImaagem);
        return produtoRepository.save(produto);
    }

    public Produto removerCategoriaDoProduto(Long produtoId, Long categoriaId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        produto.getCategorias().remove(categoria);
        return produtoRepository.save(produto);
    }
    
    public List<Produto> buscarProdutosPorNome(String nome) {
        return produtoRepository.buscarPorNomeContendo(nome.toLowerCase());
    }

    public List<Produto> buscarProdutosPorPrecoMenorQue(double preco) {
        return produtoRepository.buscarPorPrecoMenorQue(preco);
    }

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}
	

}
