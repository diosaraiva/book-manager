package br.com.diosaraiva.bookmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.service.ILivroService;
import br.com.diosaraiva.bookmanager.utils.LivroUtil;

@RestController
public class TestController {

	@Autowired
	private ILivroService livroService;

	//Mapeamento para raiz URI do servidor
	@RequestMapping("/")
	public String index() {

		return "Servidor OK!";

	}

	@RequestMapping("/test/{quantidade}")
	public String criarLivrosAleatorios(@PathVariable("quantidade") Integer quantidade) {

		livroService.adicionarListaLivros(LivroUtil.criarCenarioListaLivroTeste(quantidade));

		return quantidade.toString() + " livros aleatórios criados!";

	}
}
