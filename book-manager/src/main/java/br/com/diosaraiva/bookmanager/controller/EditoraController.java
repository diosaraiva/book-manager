package br.com.diosaraiva.bookmanager.controller;

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

import br.com.diosaraiva.bookmanager.entity.Editora;
import br.com.diosaraiva.bookmanager.service.IEditoraService;

@RestController
@RequestMapping(value= "/editoras")
public class EditoraController {

	private final Logger LOG = LoggerFactory.getLogger(EditoraController.class);

	@Autowired
	private IEditoraService editoraService;

	//CREATE - Adicionar Editora
	@CrossOrigin
	@RequestMapping(value= "/novo", method = RequestMethod.POST,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> adicionarEditora(@RequestBody Editora editora, 
			UriComponentsBuilder ucBuilder){

		LOG.info("Adicionando nova Editora: {}", editora);

		/*if (editoraService.exists(editora)){
			LOG.info("Ja existem uma Editora com o ID: " + editora.getId() + " a ser cadastrado.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}*/

		editoraService.adicionarEditora(editora);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/editoras/{id}").buildAndExpand(editora.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Editora por ID
	@CrossOrigin
	@RequestMapping(value= "/{idEditora}", method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Editora> selecionarEditoraPorId(@PathVariable("idEditora") long idEditora){

		LOG.info("Selecionando Editora com o ID: {}", idEditora);

		Editora editora = editoraService.selecionarEditoraPorId(idEditora);

		if (editora == null){
			LOG.info("Editora com o ID: {} nao encontrado.", idEditora);
			return new ResponseEntity<Editora>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Editora>(editora, HttpStatus.OK);
	}

	//RETRIEVE - Listar todas as Editoras
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Editora>> listarEditoras() {

		LOG.info("Listando todas as Editoras disponiveis");

		List<Editora> editoras = editoraService.listarEditoras();

		if (editoras == null || editoras.isEmpty()){
			LOG.info("Nenhuma Editora encontrada.");
			return new ResponseEntity<List<Editora>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Editora>>(editoras, HttpStatus.OK);

	}

	//UPDATE - Atualizar Editora
	@CrossOrigin
	@RequestMapping(value= "/{idEditora}", method = RequestMethod.PUT,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Editora> atualizarEditora(@PathVariable long idEditora, @RequestBody Editora editora){

		LOG.info("Atualizando Editora: {}", editora);

		Editora editoraAtual = editoraService.selecionarEditoraPorId(idEditora);

		if (editoraAtual == null){
			LOG.info("Editora com o ID: {} nao encontrado", idEditora);
			return new ResponseEntity<Editora>(HttpStatus.NOT_FOUND);
		}

		editoraAtual.setId(editora.getId());
		editoraAtual.setNomeEditora(editora.getNomeEditora());
		editoraAtual.setSite(editora.getSite());

		editoraService.atualizarEditora(editoraAtual);

		return new ResponseEntity<Editora>(editoraAtual, HttpStatus.OK);
	}

	//DELETE - Remover Editora
	@CrossOrigin
	@RequestMapping(value= "/{idEditora}", method = RequestMethod.DELETE,  
	produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> removerEditora(@PathVariable("idEditora") long idEditora){

		LOG.info("Removendo Editora com o ID: {}", idEditora);

		Editora editora = editoraService.selecionarEditoraPorId(idEditora);

		if (editora == null){
			LOG.info("Nao foi possivel remover Editora. ID: {} nao encontrado.", idEditora);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		editoraService.removerEditora(idEditora);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
