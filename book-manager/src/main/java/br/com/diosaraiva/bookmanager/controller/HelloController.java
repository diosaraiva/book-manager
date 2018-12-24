package br.com.diosaraiva.bookmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.service.ILivroService;
import br.com.diosaraiva.bookmanager.utils.LivroUtil;

@RestController
public class HelloController {
	
	@Autowired
	private ILivroService livroService;
	
	//Mapeamento para raiz URI do servidor
	@RequestMapping("/")
	public String index() {
		
		livroService.adicionarListaLivros(LivroUtil.criarCenarioListaLivroTeste(20));
		return "Servidor OK!";
	
	}
	
}
