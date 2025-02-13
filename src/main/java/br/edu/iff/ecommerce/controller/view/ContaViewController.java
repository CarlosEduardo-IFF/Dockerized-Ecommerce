package br.edu.iff.ecommerce.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import br.edu.iff.ecommerce.model.Administrador;
import br.edu.iff.ecommerce.model.ContaCliente;
import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.service.AdministradorService;
import br.edu.iff.ecommerce.service.ClienteService;
import br.edu.iff.ecommerce.service.ContaVendedorService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContaViewController {

	
	 @Autowired
	 private ClienteService contaClienteService;
	 
	 @Autowired
	 private ContaVendedorService contaVendedorService;
	 
	 @Autowired
	 private AdministradorService administradorService;
	 
	@GetMapping("/login")
	public String carrinhoView() {
		return "login";
	}
	
	@PostMapping("/fazerLogin")
    public String loginSubmit(@RequestParam String email, @RequestParam String senha, HttpSession session) {
		
        ContaCliente cliente = contaClienteService.buscarPorEmail(email);
        if (cliente != null && senha.equals(cliente.getSenha())) {
        	session.setAttribute("nomeCliente", cliente.getNomeUsuario());
        	session.setAttribute("idCliente", cliente.getId());
        	return "redirect:/listarProdutos2";
        }

       
        ContaVendedor vendedor = contaVendedorService.findByEmail(email);
        if (vendedor != null && senha.equals(vendedor.getSenha())) {
        	session.setAttribute("nomeVendedor", vendedor.getNomeUsuario());
        	session.setAttribute("vendedor", vendedor);
        	return "redirect:/principalVendedor";
        }

       
        Administrador admin = administradorService.findByEmail(email);
        if (admin != null && senha.equals(admin.getSenha())) {
        	session.setAttribute("nomeAdmin", admin.getNomeUsuario());
        	return "redirect:/principalAdmin";
        }

        return "error";
    }
}
