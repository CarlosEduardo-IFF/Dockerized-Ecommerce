package br.edu.iff.ecommerce.controller.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ecommerce.model.Categoria;
import br.edu.iff.ecommerce.model.ContaVendedor;
import br.edu.iff.ecommerce.model.Produto;
import br.edu.iff.ecommerce.repository.ProdutoRepository;

import br.edu.iff.ecommerce.service.CategoriaService;
import br.edu.iff.ecommerce.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ProdutoViewController {

	private static String caminhoImagens = "/app/uploads/";

	@Autowired
    private CategoriaService categoriaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/crudProduto")
	public String crudProdutoView() {
		return "redirect:/listarProdutos";
	}
	
	
	@GetMapping("/cadastroProduto")
	public ModelAndView cadastrarProdutoView() {
		List<Categoria> categorias = categoriaService.listarCategorias();
		ModelAndView modelAndView = new ModelAndView("cadastro_produto");
	    modelAndView.addObject("categorias", categorias);
	    return modelAndView;

	}
	
	@PostMapping("/criarProduto")
	public ModelAndView criarProduto(@Valid Produto produto, @RequestParam("file") MultipartFile arquivo, @RequestParam("categorias") List<Long> idsCategorias, HttpServletRequest request) {
		
		try {

			if (!arquivo.isEmpty()) {

				byte[] bytes = arquivo.getBytes();
				ContaVendedor vendedor = (ContaVendedor) request.getSession().getAttribute("vendedor");
				
				Produto produtoCriado = produtoService.criarProduto(produto, vendedor.getId());
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produtoCriado.getIdProduto()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				produto.setImagem(String.valueOf(produtoCriado.getIdProduto()) + arquivo.getOriginalFilename());
				produtoRepository.save(produtoCriado);
				
				for (Long categoriaId : idsCategorias) {
	                produtoService.adicionarCategoriaAoProduto(produtoCriado.getIdProduto(), categoriaId);
	            }
				
				
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView("confirmCadastroProd");
	    modelAndView.addObject("nomeProduto", produto.getNome());

	    return modelAndView;
	}
	
	@PostMapping("/criarProduto2")
	public String criarProduto2(@Valid Produto produto, @RequestParam("file") MultipartFile arquivo, @RequestParam("categorias") List<Long> idsCategorias, @RequestParam("idVendedor") Long idVendedor) {
		
		try {

			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();

				Produto produtoCriado = produtoService.criarProduto(produto, idVendedor);
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produtoCriado.getIdProduto()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				produto.setImagem(String.valueOf(produtoCriado.getIdProduto()) + arquivo.getOriginalFilename());
				produtoRepository.save(produtoCriado);
				
				for (Long categoriaId : idsCategorias) {
	                produtoService.adicionarCategoriaAoProduto(produtoCriado.getIdProduto(), categoriaId);
	            }
				
				
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		

	    return "redirect:/crudProduto";
	}
	
	@GetMapping("/listarProdutos")
	public ModelAndView listarProdutos() {
		
		List<Categoria> categorias = categoriaService.listarCategorias();
		List<Produto> produtos = produtoService.listarProdutos();
		ModelAndView modelAndView = new ModelAndView("CRUD_Produto");
	    modelAndView.addObject("produtos", produtos);
	    modelAndView.addObject("categorias", categorias);

	    return modelAndView;
	}
	
	@GetMapping("/editarProduto/{id}")
	public ModelAndView editarVendedor(@PathVariable("id") Long id) {
	    Optional<Produto> produto = produtoService.buscarPorId(id);
	    ModelAndView mv = new ModelAndView("produto_Edit");
	    List<Categoria> categorias = categoriaService.listarCategorias();
	    mv.addObject("produto", produto.get());
	    mv.addObject("categorias", categorias);
	    return mv;
	}

	@PostMapping("/atualizarProduto")
	public ModelAndView atualizar(@Valid @ModelAttribute("produto") Produto produto, @RequestParam("file") MultipartFile arquivo, @RequestParam("categorias") List<Long> idsCategorias) {
	    
		try {

			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();

				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produto.getIdProduto()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				produtoService.alterarImagem(produto.getIdProduto(), String.valueOf(produto.getIdProduto()) + arquivo.getOriginalFilename());
				
				produtoService.atualizarProduto(produto.getIdProduto(), produto);
				
				Set<Categoria> categoriasExistentes = new HashSet<>(categoriaService.getCategoriasDoProduto(produto.getIdProduto()));

				for (Categoria categoria : categoriasExistentes) {
				    produtoService.removerCategoriaDoProduto(produto.getIdProduto(), categoria.getIdCategoria());
				}
				
				for (Long categoriaId : idsCategorias) {
	                produtoService.adicionarCategoriaAoProduto(produto.getIdProduto(), categoriaId);
	            }
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("redirect:/listarProdutos");
	    return mv;
	}
	
	@PostMapping("/removerProduto/{id}")
    public String removerVendedor(@PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
        return "redirect:/listarProdutos";
    }
	
	@GetMapping("/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] obterImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if(imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	
	@GetMapping("/listarProdutos2")
	public ModelAndView listarProdutos2() {

		List<Produto> produtos = produtoService.listarProdutos();
		ModelAndView modelAndView = new ModelAndView("indexCliente");
	    modelAndView.addObject("produtos", produtos);
	    return modelAndView;
	}
	
	@GetMapping("/exibirProduto/{id}")
	public ModelAndView exibirProduto(@PathVariable("id") Long id) {

		Optional<Produto> produto = produtoService.buscarPorId(id);
		ModelAndView modelAndView = new ModelAndView("produto");
	    modelAndView.addObject("produto", produto.get());
	    return modelAndView;
	}
	
	
}
