package br.edu.iff.ecommerce.controller.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ecommerce.service.CarrinhoDeCompraService;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoDeCompraController {

	@Autowired
    private final CarrinhoDeCompraService carrinhoService;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("")
    public ResponseEntity<?> adicionarItemAoCarrinho(@RequestParam Long carrinhoId, @RequestParam Long produtoId, @RequestParam int quantidade) {
        try {
            carrinhoService.adicionarItem(carrinhoId, produtoId, quantidade);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/removerItemDoCarrinho/{Id}")
    public ResponseEntity<?> removerItemDoCarrinho(@RequestParam Long carrinhoId, @RequestParam Long itemId) {
        try {
            carrinhoService.removerItem(carrinhoId, itemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/esvaziarCarrinho/{Id}")
    public ResponseEntity<?> esvaziarCarrinho(@RequestParam Long carrinhoId) {
        try {
            carrinhoService.esvaziarCarrinho(carrinhoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}