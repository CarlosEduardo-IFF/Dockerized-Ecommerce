package br.edu.iff.ecommerce.controller.view;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ecommerce.model.CarrinhoDeCompra;

import br.edu.iff.ecommerce.model.ItemCarrinho;

import br.edu.iff.ecommerce.service.CarrinhoDeCompraService;
import jakarta.validation.Valid;

@Controller
public class CarrinhoViewController {

	@Autowired
    private CarrinhoDeCompraService carrinhoService;
	
	@GetMapping("/carrinho/{idCarrinho}")
	public ModelAndView carrinhoView(@PathVariable("idCarrinho") Long idC) {
		CarrinhoDeCompra carrinho= carrinhoService.obterCarrinhoPorId(idC);
		
		Collection<ItemCarrinho> itens = carrinho.getItens();
		double valorTotal = calcularValorTotal(itens);

		ModelAndView modelAndView = new ModelAndView("carrinho");
		modelAndView.addObject("valorTotal", valorTotal);
		modelAndView.addObject("itens", itens);
	    modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}
	
	@GetMapping("/adicionarAoCarrinho/{idProduto}/{idCliente}")
	public ModelAndView adicionarAoCarrinho(@PathVariable("idProduto") Long idP, @PathVariable("idCliente") Long idC) {

		carrinhoService.adicionarItem(idC, idP, 1);
		CarrinhoDeCompra carrinho = carrinhoService.obterCarrinhoPorId(idC);
		Collection<ItemCarrinho> itens = carrinho.getItens();
		double valorTotal = calcularValorTotal(itens);
		ModelAndView modelAndView = new ModelAndView("carrinho");
		modelAndView.addObject("valorTotal", valorTotal);
		modelAndView.addObject("itens", itens);
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}

	@PostMapping("/removerItem/{idCarrinho}/{idItem}")
    public ModelAndView removerItem(@PathVariable("idCarrinho") Long idCarrinho, @PathVariable("idItem") Long idItem) {
        carrinhoService.removerItem(idCarrinho, idItem);
        return carrinhoView(idCarrinho);
    }
	
	@GetMapping("/editarItem/{id}")
	public ModelAndView editarItem(@PathVariable("id") Long id) {
	    ItemCarrinho item = carrinhoService.obterItemPorId(id);
	    ModelAndView mv = new ModelAndView("item_Edit");
	    mv.addObject("item", item);
	    return mv;
	}

	@PostMapping("/atualizarItem")
	public ModelAndView atualizar(@Valid @ModelAttribute("item") ItemCarrinho item) {
		
		ItemCarrinho itemParaEditar = carrinhoService.obterItemPorId(item.getId());
	    carrinhoService.editarQuantidade(itemParaEditar.getCarrinhoDeCompra().getIdCarrinho(), item, item.getId());
	    return carrinhoView(itemParaEditar.getCarrinhoDeCompra().getIdCarrinho());
	}
	
	private double calcularValorTotal(Collection<ItemCarrinho> itens) {
	    double total = 0;
	    for (ItemCarrinho item : itens) {
	        total += item.getProduto().getPreco() * item.getQuantidade();
	    }
	    return total;
	}
}
