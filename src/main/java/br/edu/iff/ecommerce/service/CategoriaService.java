package br.edu.iff.ecommerce.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.Categoria;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.repository.CategoriaRepository;
import br.edu.iff.ecommerce.repository.ProdutoRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Categoria adicionarCategoria(String descricao) {
        Categoria existente = categoriaRepository.buscarPelaDescricao(descricao);
        if (existente != null) {
            throw new RuntimeException("Já existe uma categoria com essa descrição.");
        }
        Categoria categoria = new Categoria(descricao);
        return categoriaRepository.save(categoria);
    }

    public void removerCategoria(Long id) {
        Categoria existente = categoriaRepository.buscarPeloId(id);
        if (existente == null) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    public Categoria alterarDescricaoCategoria(Long id, String novaDescricao) {
        Categoria categoria = buscarPeloId(id);
        categoria.setDescricao(novaDescricao);
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPelaDescricao(String descricao) {
        return categoriaRepository.buscarPelaDescricao(descricao);
    }

    public Categoria buscarPeloId(Long id) {
        return categoriaRepository.buscarPeloId(id);
    }
    
    public Set<Categoria> getCategoriasDoProduto(Long idProduto) {
        Produto produto = produtoRepository.findById(idProduto)
                                          .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + idProduto));
        return produto.getCategorias();
    }
    
}