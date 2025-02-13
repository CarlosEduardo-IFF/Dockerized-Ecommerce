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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.model.Categoria;
import br.edu.iff.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("")
    public ResponseEntity<Object> adicionarCategoria(@RequestBody String descricao) {
        try {
            Categoria categoria = categoriaService.adicionarCategoria(descricao);
            return new ResponseEntity<>(categoria, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removerCategoria(@RequestParam Long id) {
        try {
            categoriaService.removerCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarDescricaoCategoria(@PathVariable Long id, @RequestBody String novaDescricao) {
        try {
            Categoria categoria = categoriaService.alterarDescricaoCategoria(id, novaDescricao);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getCategorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        try {
            List<Categoria> categorias = categoriaService.listarCategorias();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCategoriaPorId(@RequestParam Long id) {
        try {
            Categoria categoria = categoriaService.buscarPeloId(id);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}