package br.com.diosaraiva.bookmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	//Mapeamento para raiz URI do servidor
	@RequestMapping("/")
	public String index() {
		return "Servidor OK!";
	
	}
	
}
