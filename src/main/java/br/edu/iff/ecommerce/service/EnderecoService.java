package br.edu.iff.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.Endereco;
import br.edu.iff.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco criarEndereco(String rua, String numero, String cep, String bairro, String cidade, String estado) {
        Endereco endereco = new Endereco(rua, numero, cep, bairro, cidade, estado);
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarPorCidade(String cidade) {
        return enderecoRepository.buscarPorCidade(cidade);
    }

    public List<Endereco> buscarPorEstadoEBairro(String estado, String bairro) {
        return enderecoRepository.buscarPorEstadoEBairro(estado, bairro);
    }
    
    public Endereco atualizarEndereco(Long id, String rua, String numero, String cep, String bairro, String cidade, String estado) throws Exception {
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new Exception("Endereço com ID " + id + " não encontrado"));

        enderecoExistente.setRua(rua);
        enderecoExistente.setNumero(numero);
        enderecoExistente.setCep(cep);
        enderecoExistente.setBairro(bairro);
        enderecoExistente.setCidade(cidade);
        enderecoExistente.setEstado(estado);

        return enderecoRepository.save(enderecoExistente);
    }
}