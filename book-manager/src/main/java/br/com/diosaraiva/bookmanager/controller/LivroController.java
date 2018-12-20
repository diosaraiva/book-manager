package br.com.diosaraiva.bookmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.viewmodel.ILivroVM;

@Controller
@RequestMapping("user")
public class LivroController {

	@Autowired
	private ILivroVM livroVM;
	
	@GetMapping("livro/{isbn}")
	public ResponseEntity<Livro> selecionarLivroPorISBN(@PathVariable("isbn") long isbn) {
		Livro livro = livroVM.selecionarLivroPorISBN(isbn);
		return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	}
	
	@GetMapping("livros")
	public ResponseEntity<List<Livro>> listarLivros() {
		List<Livro> livros = livroVM.listarLivros();
		return new ResponseEntity<List<Livro>>(livros, HttpStatus.OK);
	}
	
	@PostMapping("livro")
	public ResponseEntity<Void> adicionarLivro(@RequestBody Livro livro, UriComponentsBuilder builder) {
                boolean flag = livroVM.adicionarLivro(livro);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/livro/{isbn}").buildAndExpand(livro.getIsbn()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("livro")
	public ResponseEntity<Livro> atualizarLivro(@RequestBody Livro livro) {
		livroVM.atualizarLivro(livro);
		return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	}
	
	@DeleteMapping("livro/{isbn}")
	public ResponseEntity<Void> removerLivro(@PathVariable("isbn") Long isbn) {
		livroVM.removerLivro(isbn);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
