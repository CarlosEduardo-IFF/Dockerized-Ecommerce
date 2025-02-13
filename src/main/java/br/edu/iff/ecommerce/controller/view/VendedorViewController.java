package br.edu.iff.ecommerce.controller.view;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.service.ContaVendedorService;
import jakarta.validation.Valid;

@Controller
public class VendedorViewController {
	
	@Autowired
    private ContaVendedorService contaVendedorService;
	
	@GetMapping("/cadastroVendedor")
	public String cadastroClienteView() {
		return "cadastro_vendedor";
	}
	
	@GetMapping("/principalVendedor")
	public String indexVendedorView() {
		return "indexVendedor";
	}
	
	@GetMapping("/crudVendedor")
	public String crudVendedorView() {
		return "redirect:/listarVendedores";
	}
	
	@PostMapping("/criarVendedor")
	public ModelAndView criarCliente(@Valid ContaVendedor vendedor) {
	
		ContaVendedor novoVendedor = contaVendedorService.criarContaVendedor(vendedor.getEmail(), vendedor.getSenha(), vendedor.getNomeUsuario(), vendedor.getDescricao(), vendedor.getCnpj());
		
		ModelAndView modelAndView = new ModelAndView("confirmarCadastroVend");
	    modelAndView.addObject("nomeVendedor", novoVendedor.getNomeUsuario());

	    return modelAndView;
	}
	
	@GetMapping("/listarVendedores")
	public ModelAndView listarVendedores() {
		
		List<ContaVendedor> vendedores = contaVendedorService.listarVendedores();
		ModelAndView modelAndView = new ModelAndView("CRUD_Vendedor");
	    modelAndView.addObject("vendedores", vendedores);

	    return modelAndView;
	}
	
	@PostMapping("/criarVendedor2")
	public String criarCliente2(@Valid ContaVendedor vendedor) {
		
		contaVendedorService.criarContaVendedor(vendedor.getEmail(), vendedor.getSenha(), vendedor.getNomeUsuario(), vendedor.getDescricao(), vendedor.getCnpj());

	    return "redirect:/crudVendedor";
	}
	
	@GetMapping("/editarVendedor/{id}")
	public ModelAndView editarVendedor(@PathVariable("id") Long id) {
	    ContaVendedor vendedor = contaVendedorService.buscarPeloId(id);
	    ModelAndView mv = new ModelAndView("vendedor_Edit");
	    mv.addObject("vendedor", vendedor);
	    return mv;
	}

	@PostMapping("/atualizarVendedor")
	public ModelAndView atualizar(@Valid @ModelAttribute("vendedor") ContaVendedor vendedor) {
	    ModelAndView mv = new ModelAndView("redirect:/listarVendedores");
	    contaVendedorService.atualizarContaVendedor(vendedor.getId(), vendedor);
	    return mv;
	}
	
	@PostMapping("/removerVendedor/{id}")
    public String removerVendedor(@PathVariable("id") Long id) {
        contaVendedorService.apagarContaVendedor(id);
        return "redirect:/listarVendedores";
    }
}