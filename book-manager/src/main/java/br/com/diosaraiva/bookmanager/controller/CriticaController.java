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

import br.com.diosaraiva.bookmanager.entity.Critica;
import br.com.diosaraiva.bookmanager.service.ICriticaService;

@RestController
@RequestMapping(value= "/criticas")
public class CriticaController {

	private final Logger LOG = LoggerFactory.getLogger(CriticaController.class);

	@Autowired
	private ICriticaService criticaService;

	//CREATE - Adicionar Critica
	@CrossOrigin
	@RequestMapping(value= "/novo", method = RequestMethod.POST,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> adicionarCritica(@RequestBody Critica critica, 
			UriComponentsBuilder ucBuilder){

		LOG.info("Adicionando nova Critica: {}", critica);

		if (criticaService.exists(critica)){
			LOG.info("Ja existem uma Critica com o ID: " + critica.getId() + " a ser cadastrado.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		criticaService.adicionarCritica(critica);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/criticas/{id}").buildAndExpand(critica.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Critica por ID
	@CrossOrigin
	@RequestMapping(value= "/{idCritica}", method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Critica> selecionarCriticaPorId(@PathVariable("idCritica") long idCritica){

		LOG.info("Selecionando Critica com o ID: {}", idCritica);

		Critica critica = criticaService.selecionarCriticaPorId(idCritica);

		if (critica == null){
			LOG.info("Critica com o ID: {} nao encontrado.", idCritica);
			return new ResponseEntity<Critica>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Critica>(critica, HttpStatus.OK);
	}

	//RETRIEVE - Listar todas as Criticas
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Critica>> listarCriticas() {

		LOG.info("Listando todas as Criticas disponiveis");

		List<Critica> criticas = criticaService.listarCriticas();

		if (criticas == null || criticas.isEmpty()){
			LOG.info("Nenhuma Critica encontrada.");
			return new ResponseEntity<List<Critica>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Critica>>(criticas, HttpStatus.OK);
		
	}

	//UPDATE - Atualizar Critica
	@CrossOrigin
	@RequestMapping(value= "/{idCritica}", method = RequestMethod.PUT,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Critica> atualizarCritica(@PathVariable long idCritica, @RequestBody Critica critica){

		LOG.info("Atualizando Critica: {}", critica);

		Critica criticaAtual = criticaService.selecionarCriticaPorId(idCritica);

		if (criticaAtual == null){
			LOG.info("Critica com o ID: {} nao encontrado", idCritica);
			return new ResponseEntity<Critica>(HttpStatus.NOT_FOUND);
		}

		criticaAtual.setId(critica.getId());
		criticaAtual.setNomeCritico(critica.getNomeCritico());
		criticaAtual.setNota(critica.getNota());
		criticaAtual.setTexto(critica.getTexto());

		criticaService.atualizarCritica(criticaAtual);

		return new ResponseEntity<Critica>(criticaAtual, HttpStatus.OK);
	}

	//UPDATE - Atualizar lista de Criticaes de um Livro
	@CrossOrigin
	@RequestMapping(value= "/lista", method = RequestMethod.PUT,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Critica>> atualizarListaCriticas(@RequestBody List<Critica> criticas){

		LOG.info("Atualizando lista de Criticas: {}", criticas);

		List<Critica> listaCriticaes = new ArrayList<>();

		for (Critica critica : criticas) {

			if(critica.getId() == null) {
				LOG.info("Adicionando nova Critica");
				criticaService.adicionarCritica(critica);
				continue;
			}

			Critica criticaAtual = criticaService.selecionarCriticaPorId(critica.getId());

			if (criticaAtual == null){
				LOG.info("Critica com o ID: {} nao encontrado", critica.getId());
				return new ResponseEntity<List<Critica>>(HttpStatus.NOT_FOUND);
			}

			criticaAtual.setId(critica.getId());
			criticaAtual.setNomeCritico(critica.getNomeCritico());
			criticaAtual.setNota(critica.getNota());
			criticaAtual.setTexto(critica.getTexto());

			criticaService.atualizarCritica(criticaAtual);

			listaCriticaes.add(criticaAtual);
		}

		return new ResponseEntity<List<Critica>>(listaCriticaes, HttpStatus.OK);
	}

	//DELETE - Remover Critica
	@CrossOrigin
	@RequestMapping(value= "/{idCritica}", method = RequestMethod.DELETE,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> removerCritica(@PathVariable("idCritica") long idCritica){

		LOG.info("Removendo Critica com o ID: {}", idCritica);

		Critica critica = criticaService.selecionarCriticaPorId(idCritica);

		if (critica == null){
			LOG.info("Nao foi possivel remover Critica. ID: {} nao encontrado.", idCritica);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		criticaService.removerCritica(idCritica);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
