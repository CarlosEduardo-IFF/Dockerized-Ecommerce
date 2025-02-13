package br.edu.iff.ecommerce.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {
	
	@GetMapping("/principalAdmin")
	public String indexAdminView() {
		return "indexAdmin";
	}

}
