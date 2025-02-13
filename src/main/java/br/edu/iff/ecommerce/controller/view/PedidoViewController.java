package br.edu.iff.ecommerce.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import br.edu.iff.ecommerce.model.Pedido;

import br.edu.iff.ecommerce.service.PedidoService;

@Controller
public class PedidoViewController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/crudPedido")
	public String crudPedidoView() {
		return "redirect:/listarPedidos";
	}
	
	@GetMapping("/realizarPedido/{id}")
	public ModelAndView fazerPedido(@PathVariable("id") Long id) {
		Long idPedido = (long) 0;
	    try {
			idPedido = pedidoService.criarPedido(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    ModelAndView mv = new ModelAndView("confirmar_Pedido");
	    mv.addObject("idPedido", idPedido);
	    return mv;
	}
	
	@PostMapping("/criarPedido/{id}")
	public String criarPedido(@PathVariable("id") Long id) {
		try {
			pedidoService.criarPedido(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return "redirect:/crudPedido";
	}
	
	@GetMapping("/listarPedidos")
	public ModelAndView listarPedidos() {
		
		List<Pedido> pedidos = pedidoService.listarTodosPedidos();
		ModelAndView modelAndView = new ModelAndView("CRUD_Pedido");
	    modelAndView.addObject("pedidos", pedidos);

	    return modelAndView;
	}
}
