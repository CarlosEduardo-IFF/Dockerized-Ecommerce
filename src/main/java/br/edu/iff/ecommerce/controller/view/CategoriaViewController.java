package br.edu.iff.ecommerce.controller.view;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ecommerce.model.Categoria;
import br.edu.iff.ecommerce.service.CategoriaService;
import jakarta.validation.Valid;

@Controller
public class CategoriaViewController {

	@Autowired
    private CategoriaService categoriaService;
	
	@GetMapping("/crudCategoria")
	public String crudCategoriaView() {
		return "redirect:/listarCategorias";
	}
	
	@PostMapping("/criarCategoria")
	public String criarCategoria(@Valid Categoria categoria) {
	    categoriaService.adicionarCategoria(categoria.getDescricao());
	    
	    return "redirect:/listarCategorias";
	}
	
	@GetMapping("/listarCategorias")
	public ModelAndView listarCategorias() {
		
		List<Categoria> categorias = categoriaService.listarCategorias();
		ModelAndView modelAndView = new ModelAndView("CRUD_Categoria");
	    modelAndView.addObject("categorias", categorias);

	    return modelAndView;
	}
	
	@GetMapping("/editarCategoria/{id}")
	public ModelAndView editarCategoria(@PathVariable("id") Long id) {
	    Categoria categoria = categoriaService.buscarPeloId(id);
	    ModelAndView mv = new ModelAndView("categoria_Edit");
	    mv.addObject("categoria", categoria);
	    return mv;
	}

	@PostMapping("/atualizarCategoria")
	public ModelAndView atualizar(@Valid @ModelAttribute("categoria") Categoria categoria) {
		
	    ModelAndView mv = new ModelAndView("redirect:/listarCategorias");
	    categoriaService.alterarDescricaoCategoria(categoria.getIdCategoria(), categoria.getDescricao());
	    return mv;
	}
	
	@PostMapping("/removerCategoria/{id}")
    public String removerCategoria(@PathVariable("id") Long id) {
        categoriaService.removerCategoria(id);
        return "redirect:/listarCategorias";
    }
}
