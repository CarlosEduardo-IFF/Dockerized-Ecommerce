package br.edu.iff.ecommerce.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ViewControllerAdvice {


	public String erroException(Exception e, Model model) {
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	 @ExceptionHandler(Exception.class)
	public String errorException(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
	    model.addAttribute("message", e.getMessage());
	    return "error";
	}
	
}
