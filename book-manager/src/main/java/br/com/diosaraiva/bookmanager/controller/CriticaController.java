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

import br.com.diosaraiva.bookmanager.model.Critica;
import br.com.diosaraiva.bookmanager.service.ICriticaService;

public class CriticaController {
	
	private final Logger LOG = LoggerFactory.getLogger(LivroController.class);

	@Autowired
	private ICriticaService criticaService;

	//CREATE - Adicionar Critica
	//Criticas não podem ser adicionadas sem Livro relacionado!!!
	@PostMapping("/criticas/novo")
	public ResponseEntity<Void> adicionarCritica(@RequestBody Critica critica, 
			UriComponentsBuilder ucBuilder){
		LOG.info("Adicionando novo Critica: {}", critica);

		if (criticaService.exists(critica)){
			LOG.info("Ja existem um Critica com o ID: " + critica.getId() + " a ser cadastrado.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		criticaService.adicionarCritica(critica);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/criticas/{id}").buildAndExpand(critica.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Critica por ID
	@GetMapping("/criticas/{id}")
	public ResponseEntity<Critica> get(@PathVariable("id") long id){
		LOG.info("Selecionando Critica com o ID: {}", id);
		Critica critica = criticaService.selecionarCriticaPorId(id);

		if (critica == null){
			LOG.info("Critica com o ID: {} nao encontrado.", id);
			return new ResponseEntity<Critica>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Critica>(critica, HttpStatus.OK);
	}

	//RETRIEVE - Listar todas as Criticas
	@GetMapping("/criticas")
	public ResponseEntity<List<Critica>> listarCriticas() {
		LOG.info("Listando todos os Criticas disponiveis");
		List<Critica> criticas = criticaService.listarCriticas();

		if (criticas == null || criticas.isEmpty()){
			LOG.info("Nenhuma Critica encontrada.");
			return new ResponseEntity<List<Critica>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Critica>>(criticas, HttpStatus.OK);
	}

	//UPDATE - Atualizar Critica
	@PutMapping("/criticas/{id}")
	public ResponseEntity<Critica> update(@PathVariable long id, @RequestBody Critica critica){
		LOG.info("Atualizando Critica: {}", critica);
		Critica criticaAtual = criticaService.selecionarCriticaPorId(id);

		if (criticaAtual == null){
			LOG.info("Critica com o ID: {} nao encontrado", id);
			return new ResponseEntity<Critica>(HttpStatus.NOT_FOUND);
		}

		criticaAtual.setId(critica.getId());
		criticaAtual.setNomeCritico(critica.getNomeCritico());
		criticaAtual.setNota(critica.getNota());
		criticaAtual.setTexto(critica.getTexto());

		criticaService.atualizarCritica(critica);

		return new ResponseEntity<Critica>(criticaAtual, HttpStatus.OK);
	}

	//DELETE - Remover Critica
	@DeleteMapping("/criticas/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id){
		LOG.info("Removendo Critica com o ID: {}", id);
		Critica critica = criticaService.selecionarCriticaPorId(id);

		if (critica == null){
			LOG.info("Nao foi possivel remover Critica. ID: {} nao encontrado.", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		criticaService.removerCritica(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
