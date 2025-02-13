package br.edu.iff.ecommerce.controller.apirest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto, @RequestParam Long idVendedor) {
        Produto novoProduto = produtoService.criarProduto(produto, idVendedor);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestParam Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@RequestParam Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/adicionar-categoria/{categoriaId}")
    public ResponseEntity<Produto> adicionarCategoriaAoProduto(@RequestParam Long produtoId, @RequestParam Long categoriaId) {
        Produto produto = produtoService.adicionarCategoriaAoProduto(produtoId, categoriaId);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/remover-categoria/{categoriaId}")
    public ResponseEntity<Produto> removerCategoriaDoProduto(@RequestParam Long produtoId, @RequestParam Long categoriaId) {
        Produto produto = produtoService.removerCategoriaDoProduto(produtoId, categoriaId);
        return ResponseEntity.ok(produto);
    }
    
    @GetMapping("/buscar-nome/{nome}")
    public ResponseEntity<List<Produto>> buscarProdutosPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarProdutosPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/preco-menor-que/{preco}")
    public ResponseEntity<List<Produto>> buscarProdutosPorPrecoMenorQue(@RequestParam double preco) {
        List<Produto> produtos = produtoService.buscarProdutosPorPrecoMenorQue(preco);
        return ResponseEntity.ok(produtos);
    }
    
    
}