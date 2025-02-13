package br.edu.iff.ecommerce.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.CarrinhoDeCompra;

import br.edu.iff.ecommerce.model.ContaCliente;
import br.edu.iff.ecommerce.model.Endereco;
import br.edu.iff.ecommerce.repository.CarrinhoDeCompraRepository;
import br.edu.iff.ecommerce.repository.ClienteRepository;
import br.edu.iff.ecommerce.repository.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository contaClienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CarrinhoDeCompraRepository carrinhoDeCompraRepository;

    public ContaCliente criarContaCliente(String email, String senha, String nomeUsuario, String cpf, LocalDate data) {
        if (contaClienteRepository.buscarPorEmail(email) != null) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        
        if (contaClienteRepository.buscarPorCpf(cpf) != null) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
        carrinhoDeCompraRepository.save(carrinho);

        ContaCliente novaContaCliente = new ContaCliente(email, senha, nomeUsuario, cpf, carrinho, data);
        return contaClienteRepository.save(novaContaCliente);
    }

    public void removerCliente(Long id) {
        contaClienteRepository.removerPorId(id);
        carrinhoDeCompraRepository.deleteById(id);
    }

    public void alterarCredito(Long id, double novoCredito) {
        contaClienteRepository.alterarCredito(id, novoCredito);
    }

    public void alterarNome(Long id, String novoNome) {
        contaClienteRepository.alterarNome(id, novoNome);
    }

    public Optional<ContaCliente> buscarPorId(Long id) {
        return contaClienteRepository.findById(id);
    }

    public ContaCliente buscarPorEmail(String email) {
        return contaClienteRepository.buscarPorEmail(email);
    }

    public ContaCliente buscarPorCpf(String cpf) {
        return contaClienteRepository.buscarPorCpf(cpf);
    }

	public Endereco addEndereco(Long idCliente, Long idEndereco) {
		Optional<ContaCliente> cliente = contaClienteRepository.findById(idCliente);
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);

		cliente.get().addEndereco(endereco.get());
		contaClienteRepository.save(cliente.get());
		endereco.get().setContaCliente(cliente.get());
		enderecoRepository.save(endereco.get());

		return endereco.get();
	}

	public List<ContaCliente> listarClientes() {
		return contaClienteRepository.findAll();
	}
	
	public ContaCliente atualizarCliente(Long id, ContaCliente cliente) {
    	ContaCliente existingCliente = contaClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrada com o ID: " + id));
        
        existingCliente.setCpf(cliente.getCpf());
        existingCliente.setData(cliente.getData());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setNomeUsuario(cliente.getNomeUsuario());
        existingCliente.setSenha(cliente.getSenha());
        
       return contaClienteRepository.save(existingCliente);
    }
	
	public ContaCliente buscarPeloId(Long id) {
        return contaClienteRepository.buscarPorId(id);
    }

}