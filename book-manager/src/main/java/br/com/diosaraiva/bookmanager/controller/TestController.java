package br.com.diosaraiva.bookmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.entity.LivroExtenso;
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

	//Funcionalidade de Teste, deve ser removida em Produção!
	@RequestMapping("/test/{quantidade}")
	public String criarLivrosAleatorios(@PathVariable("quantidade") Integer quantidade) {

		List<LivroExtenso> livros = livroService.listarLivrosExtenso();

		for (LivroExtenso livroExtenso : livros) {

			livroService.removerLivro(livroExtenso.getIsbn());
			//Não está apagando Autores, Editora e Críticas relacionadas!!
			
		}

		livroService.adicionarListaLivros(LivroUtil.criarListaLivroTeste(quantidade));

		return quantidade.toString() + " livros aleatórios criados!";

	}
}
