package br.edu.iff.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ecommerce.model.Status;
import br.edu.iff.ecommerce.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Status criarStatus(String descricao) {
        Status status = new Status();
        status.setDescricao(descricao);
        return statusRepository.save(status);
    }

    public Status atualizarDescricaoStatus(Long id, String novaDescricao) {
        Status status = statusRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Status não encontrado"));
        status.setDescricao(novaDescricao);
        return statusRepository.save(status);
    }

    public void apagarStatus(Long id) {
        statusRepository.deleteById(id);
    }
    
    public Status encontrarPorDescricao(String descricao) {
        return statusRepository.encontrarPorDescricao(descricao);
    }
}
