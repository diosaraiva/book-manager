package br.com.diosaraiva.bookmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.model.entity.Livro;
import br.com.diosaraiva.bookmanager.viewmodel.LivroVM;

@RestController
public class LivroController {

	LivroVM livroVM;

	@GetMapping("/livros/{isbn}")
	public Optional<Livro> selecionarLivro(@PathVariable long isbn) {
		return livroVM.getLivro(isbn);
	}

	@GetMapping("/livros")
	public List<Livro> listarLivros() {
		return livroVM.getLivros();
	}

	@PostMapping("/livros/{livro}")
	public void adicionarLivro(@PathVariable Livro livro){
		livroVM.setLivro(livro);
	}

	@PutMapping("/livros/{livro}")
	public void atualizarLivro(@PathVariable Livro livro){    
		livroVM.setLivro(livro);
	}

	@DeleteMapping("/livros/{id}")
	public Livro removerLivro(@PathVariable Long id){
		return null;
	}
	
	@GetMapping("/livros/autor/{idAutor}")
	public List<Livro> listarLivrosPorAutor(@PathVariable long idAutor) {
		return null;
	}
	
	@GetMapping("/livros/{preco}")
	public String valorPorExtenso(@PathVariable double preco) {
		return null;
	}
}
