package br.edu.iff.ecommerce.controller.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerGlobaAdvice {

	public ResponseEntity errorException(Exception e) {
		Error erro = new Error();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
