package br.edu.iff.ecommerce.controller.view;


import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.service.CategoriaService;
import br.edu.iff.ecommerce.service.AdministradorService;
import br.edu.iff.ecommerce.service.ClienteService;
import br.edu.iff.ecommerce.service.ContaVendedorService;
import br.edu.iff.ecommerce.service.ProdutoService;
import br.edu.iff.ecommerce.service.StatusService;

@Controller
public class MainControllerView {

	@Autowired
	    private AdministradorService administrador;
	
	 @Autowired
	    private StatusService statusService;
	 
	 @Autowired
	    private ClienteService contaClienteService;
	 
	 @Autowired
	    private ContaVendedorService contaVendedorService;
	 
	 @Autowired
	    private CategoriaService categoriaService;
	 
	 @Autowired
		private ProdutoService produtoService;
	 
	 private int vInicializacao = 0;
	
	@GetMapping("/principal")
	public ModelAndView mainview() {
		
		if(vInicializacao == 0) {
			
			//Cadastro de status
			statusService.criarStatus("Aguardando resposta");
			statusService.criarStatus("Em preparação");
			statusService.criarStatus("Enviado");
			statusService.criarStatus("Entregue");

			administrador.criarAdministrador("admin@gmail.com", "admin1234", "Admin", "14758269356");
			
			//Cadastro de cliente
			LocalDate data1 = LocalDate.of(1990, 5, 15);
	        contaClienteService.criarContaCliente("carlos@gmail.com", "carlos1234", "Carlos", "99999999999", data1);
	        LocalDate data2 = LocalDate.of(1985, 10, 25);
	        contaClienteService.criarContaCliente("maria@gmail.com", "maria5678", "Maria", "88888888888", data2);
	        LocalDate data3 = LocalDate.of(1980, 3, 8);
	        contaClienteService.criarContaCliente("joao@gmail.com", "joao9999", "João", "77777777777", data3);
	        
	        //Cadastro de Vendedor
	        contaVendedorService.criarContaVendedor("vendeletro@gmail.com", "eletro123", "Vendedor Eletro", "Venda de eletrodomésticos", "12345678901234");
	        contaVendedorService.criarContaVendedor("vendsmartphones@gmail.com", "smart567", "Vendedor Smartphones", "Venda de smartphones", "98765432109876");
	        contaVendedorService.criarContaVendedor("vendvideogames@gmail.com", "games999", "Vendedor Videogames", "Venda de videogames", "45678901234567");
	        
	        //Cadastro de Categorias
	        categoriaService.adicionarCategoria("Eletrodomésticos");
	        categoriaService.adicionarCategoria("Smartphones");
	        categoriaService.adicionarCategoria("Videogames");
	        
	        //Cadastro de Produtos
	        Produto p1 = new Produto();
	        p1.setDescricao("Uma lavadora de roupas");
	        p1.setQuantDisponivel(100);
	        p1.setPreco(1849);
	        p1.setNome("Lavadora");
	        p1.setImagem("1LavadoradeRoupas.avif");
	        produtoService.criarProduto(p1, (long) 1);

	        Produto p2 = new Produto();
	        p2.setDescricao("Uma geladeira");
	        p2.setQuantDisponivel(30);
	        p2.setPreco(2699);
	        p2.setNome("Geladeira");
	        p2.setImagem("2Geladeira.avif");
	        produtoService.criarProduto(p2, (long) 1);

	        Produto p3 = new Produto();
	        p3.setDescricao("Um forno de micro-ondas");
	        p3.setQuantDisponivel(40);
	        p3.setPreco(599);
	        p3.setNome("Micro-ondas");
	        p3.setImagem("3FornodeMicro-ondas.avif");
	        produtoService.criarProduto(p3, (long) 1);

	        Produto p4 = new Produto();
	        p4.setDescricao("Um fogão");
	        p4.setQuantDisponivel(25);
	        p4.setPreco(899);
	        p4.setNome("Fogão");
	        p4.setImagem("4Fogao.avif");
	        produtoService.criarProduto(p4, (long) 1);

	        Produto p5 = new Produto();
	        p5.setDescricao("Um cooktop");
	        p5.setQuantDisponivel(35);
	        p5.setPreco(560);
	        p5.setNome("Cooktop");
	        p5.setImagem("5Cooktop.avif");
	        produtoService.criarProduto(p5, (long) 1);

	        Produto p6 = new Produto();
	        p6.setDescricao("Um smartphone Motorola Razr 40");
	        p6.setQuantDisponivel(40);
	        p6.setPreco(4559);
	        p6.setNome("Motorola Razr 40");
	        p6.setImagem("6MotorolaRazr40.avif");
	        produtoService.criarProduto(p6, (long) 2);

	        Produto p7 = new Produto();
	        p7.setDescricao("Um iPhone 13");
	        p7.setQuantDisponivel(30);
	        p7.setPreco(3699);
	        p7.setNome("iPhone 13");
	        p7.setImagem("7iPhone13.avif");
	        produtoService.criarProduto(p7, (long) 2);

	        Produto p8 = new Produto();
	        p8.setDescricao("Um Samsung Galaxy A54");
	        p8.setQuantDisponivel(50);
	        p8.setPreco(1699);
	        p8.setNome("Galaxy A54");
	        p8.setImagem("8SamsungGalaxyA54.avif");
	        produtoService.criarProduto(p8, (long) 2);

	        Produto p9 = new Produto();
	        p9.setDescricao("Um Samsung Galaxy S23");
	        p9.setQuantDisponivel(45);
	        p9.setPreco(2699);
	        p9.setNome("Galaxy S23");
	        p9.setImagem("9SamsungGalaxyS23.avif");
	        produtoService.criarProduto(p9, (long) 2);

	        Produto p10 = new Produto();
	        p10.setDescricao("Um smartphone Xiaomi Redmi 13C");
	        p10.setQuantDisponivel(50);
	        p10.setPreco(1299);
	        p10.setNome("Xiaomi Redmi 13C");
	        p10.setImagem("10XiaomiRedmi13C.avif");
	        produtoService.criarProduto(p10, (long) 2);

	        Produto p11 = new Produto();
	        p11.setDescricao("Um console de videogame PlayStation 5");
	        p11.setQuantDisponivel(20);
	        p11.setPreco(3799);
	        p11.setNome("PlayStation 5");
	        p11.setImagem("11Playstation5.avif");
	        produtoService.criarProduto(p11, (long) 3);

	        Produto p12 = new Produto();
	        p12.setDescricao("Um console de videogame Xbox Series S");
	        p12.setQuantDisponivel(15);
	        p12.setPreco(2509);
	        p12.setNome("Xbox Series S");
	        p12.setImagem("12XboxSeriesS.avif");
	        produtoService.criarProduto(p12, (long) 3);
	        
	        vInicializacao++;
		}
        
        List<Produto> produtos = produtoService.listarProdutos();
		ModelAndView modelAndView = new ModelAndView("index");
	    modelAndView.addObject("produtos", produtos);
	    return modelAndView;
       
	}
	
	@GetMapping("/404")
	public String teste() {
		
		return "teste";
	}
	
}
