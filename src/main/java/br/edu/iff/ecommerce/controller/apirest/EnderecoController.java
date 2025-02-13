package br.edu.iff.ecommerce.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.service.EnderecoService;
import br.edu.iff.ecommerce.model.Endereco;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("")
    public ResponseEntity<Endereco> criarEndereco(@RequestParam String rua,
                                                  @RequestParam String numero,
                                                  @RequestParam String cep,
                                                  @RequestParam String bairro,
                                                  @RequestParam String cidade,
                                                  @RequestParam String estado) {
        Endereco novoEndereco = enderecoService.criarEndereco(rua, numero, cep, bairro, cidade, estado);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Endereco>> buscarPorCidade(@PathVariable String cidade) {
        List<Endereco> enderecos = enderecoService.buscarPorCidade(cidade);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/estado-bairro")
    public ResponseEntity<List<Endereco>> buscarPorEstadoEBairro(@RequestParam String estado, @RequestParam String bairro) {
        List<Endereco> enderecos = enderecoService.buscarPorEstadoEBairro(estado, bairro);
        return ResponseEntity.ok(enderecos);
    }

}