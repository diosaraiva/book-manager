package br.com.diosaraiva.bookmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.diosaraiva.bookmanager.entity.Autor;
import br.com.diosaraiva.bookmanager.service.IAutorService;

@RestController
@RequestMapping(value= "/autores")
public class AutorController {

	private final Logger LOG = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private IAutorService autorService;

	//CREATE - Adicionar Autor
	@CrossOrigin
	@RequestMapping(value= "/novo", method = RequestMethod.POST,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> adicionarAutor(@RequestBody Autor autor, 
			UriComponentsBuilder ucBuilder){

		LOG.info("Adicionando novo Autor: {}", autor);

		/*if (autorService.exists(autor)){
			LOG.info("Ja existem um Autor com o ID: " + autor.getId() + " a ser cadastrado.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}*/

		autorService.adicionarAutor(autor);

		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Autor por ID
	@CrossOrigin
	@RequestMapping(value= "/{idAutor}", method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Autor> selecionarAutorPorId(@PathVariable("idAutor") long idAutor){

		LOG.info("Selecionando Autor com o ID: {}", idAutor);

		Autor autor = autorService.selecionarAutorPorId(idAutor);

		if (autor == null){
			LOG.info("Autor com o ID: {} nao encontrado.", idAutor);
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}

	//RETRIEVE - Listar todos os Autores
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
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
	@CrossOrigin
	@RequestMapping(value= "/{idAutor}", method = RequestMethod.PUT,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Autor> atualizarAutor(@PathVariable long idAutor, @RequestBody Autor autor){

		LOG.info("Atualizando Autor: {}", autor);

		Autor autorAtual = autorService.selecionarAutorPorId(idAutor);

		if (autorAtual == null){
			LOG.info("Autor com o ID: {} nao encontrado", idAutor);
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}

		autorAtual.setId(autor.getId());
		autorAtual.setNomeAutor(autor.getNomeAutor());
		autorAtual.setNacionalidade(autor.getNacionalidade());

		autorService.atualizarAutor(autorAtual);

		return new ResponseEntity<Autor>(autorAtual, HttpStatus.OK);
	}

	//UPDATE - Atualizar lista de Autores de um Livro
	@CrossOrigin
	@RequestMapping(value= "/lista", method = RequestMethod.PUT,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Autor>> atualizarListaAutores(@RequestBody List<Autor> autores){

		LOG.info("Atualizando lista de Autores: {}", autores);

		List<Autor> listaAutores = new ArrayList<>();

		for (Autor autor : autores) {

			if(autor.getId() == null) {
				LOG.info("Adicionando novo Autor");
				autorService.adicionarAutor(autor);
				continue;
			}

			Autor autorAtual = autorService.selecionarAutorPorId(autor.getId());

			if (autorAtual == null){
				LOG.info("Autor com o ID: {} nao encontrado", autor.getId());
				return new ResponseEntity<List<Autor>>(HttpStatus.NOT_FOUND);
			}

			autorAtual.setId(autor.getId());
			autorAtual.setNomeAutor(autor.getNomeAutor());
			autorAtual.setNacionalidade(autor.getNacionalidade());

			autorService.atualizarAutor(autorAtual);

			listaAutores.add(autorAtual);
		}

		return new ResponseEntity<List<Autor>>(listaAutores, HttpStatus.OK);
	}

	//DELETE - Remover Autor
	@CrossOrigin
	@RequestMapping(value= "/{idAutor}", method = RequestMethod.DELETE,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> removerAutor(@PathVariable("idAutor") long idAutor){

		LOG.info("Removendo Autor com o ID: {}", idAutor);

		Autor autor = autorService.selecionarAutorPorId(idAutor);

		if (autor == null){
			LOG.info("Nao foi possivel remover Autor. ID: {} nao encontrado.", idAutor);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		autorService.removerAutor(idAutor);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
