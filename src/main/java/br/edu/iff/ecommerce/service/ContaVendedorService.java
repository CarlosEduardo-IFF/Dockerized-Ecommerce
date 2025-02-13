package br.edu.iff.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.repository.ContaVendedorRepository;

@Service
public class ContaVendedorService {

    @Autowired
    private ContaVendedorRepository contaVendedorRepository;

    public ContaVendedor criarContaVendedor(String email, String senha, String nomeUsuario, String descricao, String cnpj) {
    	
    	ContaVendedor contaVendedor = new ContaVendedor(email, senha, nomeUsuario, descricao, cnpj);
        contaVendedorRepository.save(contaVendedor);
        return contaVendedor;
    }

    public void atualizarContaVendedor(Long id, ContaVendedor contaVendedor) {
        ContaVendedor existingContaVendedor = contaVendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta do vendedor não encontrada com o ID: " + id));
        
        existingContaVendedor.setDescricao(contaVendedor.getDescricao());
        existingContaVendedor.setCnpj(contaVendedor.getCnpj());
        existingContaVendedor.setEmail(contaVendedor.getEmail());
        existingContaVendedor.setNomeUsuario(contaVendedor.getNomeUsuario());
        existingContaVendedor.setSenha(contaVendedor.getSenha());
        
        contaVendedorRepository.save(existingContaVendedor);
    }

    public void apagarContaVendedor(Long id) {
        contaVendedorRepository.deleteById(id);
    }

    public void adicionarProduto(Long id, Produto produto) {
        ContaVendedor contaVendedor = contaVendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta do vendedor não encontrada com o ID: " + id));
        
        contaVendedor.getProdutos().add(produto);
        contaVendedorRepository.save(contaVendedor);
    }
    
    public List<ContaVendedor> encontrarPorQuantidadeDeVendas(int quantidade) {
        return contaVendedorRepository.encontrarPorQuantidadeDeVendas(quantidade);
    }

    public List<ContaVendedor> encontrarPorPontuacaoDeAvaliacao(int pontuacao) {
        return contaVendedorRepository.encontrarPorPontuacaoDeAvaliacao(pontuacao);
    }

	public ContaVendedor findByEmail(String email) {
		 return contaVendedorRepository.findByEmail(email);
	}

	public List<ContaVendedor> listarVendedores() {
		return contaVendedorRepository.findAll();
	}

	public ContaVendedor buscarPeloId(Long id) {
		return contaVendedorRepository.buscarPorId(id);
	}
	
}
