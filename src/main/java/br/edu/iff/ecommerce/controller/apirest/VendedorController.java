package br.edu.iff.ecommerce.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.service.ContaVendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private ContaVendedorService contaVendedorService;

    @PostMapping("")
    public ContaVendedor criarContaVendedor(String email, String senha, String nomeUsuario, String descricao, String cnpj) {
        ContaVendedor contaVendedor = contaVendedorService.criarContaVendedor(email, senha,nomeUsuario, descricao, cnpj);
        return contaVendedor;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarContaVendedor(@PathVariable Long id, @RequestBody ContaVendedor contaVendedor) {
        contaVendedorService.atualizarContaVendedor(id, contaVendedor);
        return ResponseEntity.ok("Conta do vendedor atualizada com sucesso.");
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<String> apagarContaVendedor(@PathVariable Long id) {
        contaVendedorService.apagarContaVendedor(id);
        return ResponseEntity.ok("Conta do vendedor apagada com sucesso.");
    }

    @PostMapping("/{id}/produtos/adicionar")
    public ResponseEntity<String> adicionarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        contaVendedorService.adicionarProduto(id, produto);
        return ResponseEntity.ok("Produto adicionado à lista do vendedor com sucesso.");
    }
    
    @GetMapping("/quantidade-vendas/{quantidade}")
    public ResponseEntity<List<ContaVendedor>> encontrarPorQuantidadeDeVendas(@PathVariable int quantidade) {
        List<ContaVendedor> contas = contaVendedorService.encontrarPorQuantidadeDeVendas(quantidade);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/por-pontuacao-avaliacao/{pontuacao}")
    public ResponseEntity<List<ContaVendedor>> encontrarPorPontuacaoDeAvaliacao(@PathVariable int pontuacao) {
        List<ContaVendedor> contas = contaVendedorService.encontrarPorPontuacaoDeAvaliacao(pontuacao);
        return ResponseEntity.ok(contas);
    }
    
    @GetMapping("/getVendedores")
    public ResponseEntity<List<ContaVendedor>> listarVendedores() {
        try {
            List<ContaVendedor> vendedores = contaVendedorService.listarVendedores();
            return new ResponseEntity<>(vendedores, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}