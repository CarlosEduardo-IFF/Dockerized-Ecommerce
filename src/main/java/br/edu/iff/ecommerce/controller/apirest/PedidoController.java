package br.edu.iff.ecommerce.controller.apirest;

import java.util.List;

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

import br.edu.iff.ecommerce.model.Pedido;
import br.edu.iff.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Long> criarPedido(@RequestParam Long clienteId) {
        try {
            Long pedidoId = pedidoService.criarPedido(clienteId);
            return ResponseEntity.ok(pedidoId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        try {
            pedidoService.cancelarPedido(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public List<Pedido> listarTodosPedidos() throws Exception{
       return pedidoService.listarTodosPedidos();
    }
    
    @PutMapping("/{Id}/atualizar-status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long pedidoId, @RequestParam Long novoStatusId) {
        try {
            pedidoService.atualizarStatus(pedidoId, novoStatusId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}