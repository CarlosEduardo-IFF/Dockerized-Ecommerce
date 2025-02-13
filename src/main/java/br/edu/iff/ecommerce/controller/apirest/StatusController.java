package br.edu.iff.ecommerce.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.service.StatusService;
import br.edu.iff.ecommerce.model.Status;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("")
    public ResponseEntity<Status> criarStatus(@RequestParam String descricao) {
        Status status = statusService.criarStatus(descricao);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Status> atualizarDescricaoStatus(@PathVariable Long id, @RequestParam String novaDescricao) {
        Status status = statusService.atualizarDescricaoStatus(id, novaDescricao);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> apagarStatus(@PathVariable Long id) {
        statusService.apagarStatus(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Status> encontrarPorDescricao(@PathVariable String descricao) {
        Status status = statusService.encontrarPorDescricao(descricao);
        return ResponseEntity.ok(status);
    }
}
