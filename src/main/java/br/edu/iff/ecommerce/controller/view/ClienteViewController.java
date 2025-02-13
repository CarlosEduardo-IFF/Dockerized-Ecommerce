package br.edu.iff.ecommerce.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ecommerce.model.ContaCliente;
import br.edu.iff.ecommerce.model.Endereco;
import br.edu.iff.ecommerce.service.ClienteService;
import br.edu.iff.ecommerce.service.EnderecoService;
import jakarta.validation.Valid;

@Controller
public class ClienteViewController {
	
	@Autowired
    private EnderecoService enderecoService;
	
	@Autowired
    private ClienteService contaClienteService;
	
	@GetMapping("/cadastroCliente")
	public String cadastroClienteView() {
		return "cadastro";
	}
	
	@GetMapping("/crudCliente")
	public String crudClienteView() {
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/principalCliente")
	public String indexClienteView() {
		return "redirect:/listarProdutos2";
	}
	
	@PostMapping("/criarCliente")
	public ModelAndView criarCliente(@Valid ContaCliente cliente, @Valid Endereco endereco) {
		
		Endereco novoEndereco = enderecoService.criarEndereco(endereco.getRua(), endereco.getNumero(), endereco.getCep(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
		ContaCliente novoCliente = contaClienteService.criarContaCliente(cliente.getEmail(), cliente.getSenha(), cliente.getNomeUsuario(), cliente.getCpf(), cliente.getData());
		
		contaClienteService.addEndereco(novoCliente.getId(), novoEndereco.getId());
		
		ModelAndView modelAndView = new ModelAndView("confirmarcadastro");
	    modelAndView.addObject("nomeCliente", cliente.getNomeUsuario());

	    return modelAndView;
	}
	
	@PostMapping("/criarCliente2")
	public String criarCliente2(@Valid ContaCliente cliente, @RequestParam("cep[]")String[] cep, @RequestParam("cidade[]")String[] cidade, @RequestParam("numero[]")String[] numero, @RequestParam("estado[]")String[] estado, @RequestParam("rua[]")String[] rua, @RequestParam("bairro[]")String[] bairro) {
		
		ContaCliente novoCliente = contaClienteService.criarContaCliente(cliente.getEmail(), cliente.getSenha(), cliente.getNomeUsuario(), cliente.getCpf(), cliente.getData());
		
		for (int i = 0; i < cep.length; i++) {
			System.out.println(rua[i]);
            Endereco endereco1 = enderecoService.criarEndereco(rua[i], numero[i], cep[i], bairro[i], cidade[i], estado[i]);
            contaClienteService.addEndereco(novoCliente.getId(), endereco1.getId());
        }
		
		

	    return "redirect:/crudCliente";
	}
	
	@GetMapping("/confirmacaoCadastro")
	public String viewConfirmacaoCadastro() {
		return "confirmarcadastro";
	}
	
	@GetMapping("/listarClientes")
	public ModelAndView listarClientes() {
		
		List<ContaCliente> clientes = contaClienteService.listarClientes();
		ModelAndView modelAndView = new ModelAndView("CRUD_Cliente");
	    modelAndView.addObject("clientes", clientes);

	    return modelAndView;
	}

	@GetMapping("/editarCliente/{id}")
	public ModelAndView editarCliente(@PathVariable("id") Long id) {
	    ContaCliente cliente = contaClienteService.buscarPeloId(id);
	    ModelAndView mv = new ModelAndView("cliente_Edit");
	    mv.addObject("cliente", cliente);
	    return mv;
	}

	@PostMapping("/atualizarCliente")
	public ModelAndView atualizar(@Valid @ModelAttribute("cliente") ContaCliente cliente) {
	    ModelAndView mv = new ModelAndView("redirect:/listarClientes");
	    contaClienteService.atualizarCliente(cliente.getId(), cliente);
	    return mv;
	}
	
	@PostMapping("/removerCliente/{id}")
    public String removerCliente(@PathVariable("id") Long id) {
        contaClienteService.removerCliente(id);
        return "redirect:/listarClientes";
    }
	
	
}
