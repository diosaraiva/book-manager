package br.com.diosaraiva.bookmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.service.ILivroService;

@RestController
public class LivroController {

	private final Logger LOG = LoggerFactory.getLogger(LivroController.class);
	
	@Autowired
	private ILivroService livroService;

	//CREATE - Adicionar Livro
	@PostMapping("/livros/novo")
	public ResponseEntity<Void> adicionarLivro(@RequestBody Livro livro, 
			UriComponentsBuilder ucBuilder){
        LOG.info("Adicionando novo Livro: {}", livro);

        if (livroService.exists(livro)){
            LOG.info("Ja existem um Livro com o ISBN: " + livro.getIsbn() + " a ser cadastrado.");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        livroService.adicionarLivro(livro);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/livros/{isbn}").buildAndExpand(livro.getIsbn()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	//RETRIEVE - Selcionar livro por ISBN
	@GetMapping("/livros/{isbn}")
	public ResponseEntity<Livro> get(@PathVariable("isbn") long isbn){
        LOG.info("Selecionando Livro com o ISBN: {}", isbn);
        Livro livro = livroService.selecionarLivroPorISBN(isbn);

        if (livro == null){
            LOG.info("Livro com o ISBN: {} nao encontrado.", isbn);
            return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Livro>(livro, HttpStatus.OK);
    }

	//RETRIEVE - Listar todos os Livros
	@GetMapping("/livros")
	public ResponseEntity<List<Livro>> listarLivros() {
        LOG.info("Listando todos os Livros disponiveis");
        List<Livro> livros = livroService.listarLivros();

        if (livros == null || livros.isEmpty()){
            LOG.info("Nenhum Livro encontrado.");
            return new ResponseEntity<List<Livro>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Livro>>(livros, HttpStatus.OK);
    }

	//UPDATE - Atualizar Livro
	@PutMapping("/livros/{isbn}")
	public ResponseEntity<Livro> update(@PathVariable long isbn, @RequestBody Livro livro){
        LOG.info("Atualizando Livro: {}", livro);
        Livro livroAtual = livroService.selecionarLivroPorISBN(isbn);

        if (livroAtual == null){
            LOG.info("Livro com o ISBN: {} nao encontrado", isbn);
            return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
        }

        livroAtual.setIsbn(livro.getIsbn());
        livroAtual.setTitulo(livro.getTitulo());
        livroAtual.setDataPublicacao(livro.getDataPublicacao());
        livroAtual.setPreco(livro.getPreco());
        livroAtual.setSinopse(livro.getSinopse());
        livroAtual.setAutores(livro.getAutores());
        livroAtual.setEditora(livro.getEditora());
        livroAtual.setCriticas(livro.getCriticas());

        livroService.atualizarLivro(livro);
        
        return new ResponseEntity<Livro>(livroAtual, HttpStatus.OK);
    }

	//DELETE - Remover Livro
	@DeleteMapping("/livros/{isbn}")
	public ResponseEntity<Void> delete(@PathVariable("isbn") long isbn){
        LOG.info("Removendo Livro com o ISBN: {}", isbn);
        Livro livro = livroService.selecionarLivroPorISBN(isbn);

        if (livro == null){
            LOG.info("Nao foi possivel remover Livro. ISBN: {} nao encontrado.", isbn);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        livroService.removerLivro(isbn);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
