package br.com.diosaraiva.bookmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.service.IAutorService;

public class AutorController {

	private final Logger LOG = LoggerFactory.getLogger(LivroController.class);

	@Autowired
	private IAutorService autorService;

	//CREATE - Adicionar Autor
	@PostMapping("/autores/novo")
	public ResponseEntity<Void> adicionarAutor(@RequestBody Autor autor, 
			UriComponentsBuilder ucBuilder){
		LOG.info("Adicionando novo Autor: {}", autor);

		if (autorService.exists(autor)){
			LOG.info("Ja existem um Autor com o ID: " + autor.getId() + " a ser cadastrado.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		autorService.adicionarAutor(autor);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Autor por ID
	@GetMapping("/autores/{id}")
	public ResponseEntity<Autor> get(@PathVariable("id") long id){
		LOG.info("Selecionando Autor com o ID: {}", id);
		Autor autor = autorService.selecionarAutorPorId(id);

		if (autor == null){
			LOG.info("Autor com o ID: {} nao encontrado.", id);
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}

	//RETRIEVE - Listar todos os Autores
	@GetMapping("/autores")
	public ResponseEntity<List<Autor>> listarAutores() {
		LOG.info("Listando todos os Autores disponiveis");
		List<Autor> autores = autorService.listarAutores();

		if (autores == null || autores.isEmpty()){
			LOG.info("Nenhum Autor encontrado.");
			return new ResponseEntity<List<Autor>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Autor>>(autores, HttpStatus.OK);
	}

	//UPDATE - Atualizar Autor
	@PutMapping("/autores/{id}")
	public ResponseEntity<Autor> update(@PathVariable long id, @RequestBody Autor autor){
		LOG.info("Atualizando Autor: {}", autor);
		Autor autorAtual = autorService.selecionarAutorPorId(id);

		if (autorAtual == null){
			LOG.info("Autor com o ID: {} nao encontrado", id);
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}

		autorAtual.setId(autor.getId());
		autorAtual.setNomeAutor(autor.getNomeAutor());
		autorAtual.setNacionalidade(autor.getNacionalidade());
		autorAtual.setNascimento(autor.getNascimento());

		autorService.atualizarAutor(autor);

		return new ResponseEntity<Autor>(autorAtual, HttpStatus.OK);
	}

	//DELETE - Remover Autor
	@DeleteMapping("/autores/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id){
		LOG.info("Removendo Autor com o ID: {}", id);
		Autor autor = autorService.selecionarAutorPorId(id);

		if (autor == null){
			LOG.info("Nao foi possivel remover Autor. ID: {} nao encontrado.", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		autorService.removerAutor(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
