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

import br.com.diosaraiva.bookmanager.model.Editora;
import br.com.diosaraiva.bookmanager.service.IEditoraService;

public class EditoraController {

	private final Logger LOG = LoggerFactory.getLogger(LivroController.class);

	@Autowired
	private IEditoraService editoraService;

	//CREATE - Adicionar Editora
	@PostMapping("/editoras/novo")
	public ResponseEntity<Void> adicionarEditora(@RequestBody Editora editora, 
			UriComponentsBuilder ucBuilder){
		LOG.info("Adicionando nova Editora: {}", editora);

		if (editoraService.exists(editora)){
			LOG.info("Ja existem uma Editora com o ID: " + editora.getId() + " a ser cadastrada.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		editoraService.adicionarEditora(editora);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/editoras/{id}").buildAndExpand(editora.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//RETRIEVE - Selcionar Editora por ID
	@GetMapping("/editoras/{id}")
	public ResponseEntity<Editora> get(@PathVariable("id") long id){
		LOG.info("Selecionando Editora com o ID: {}", id);
		Editora editora = editoraService.selecionarEditoraPorId(id);

		if (editora == null){
			LOG.info("Editora com o ID: {} nao encontrado.", id);
			return new ResponseEntity<Editora>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Editora>(editora, HttpStatus.OK);
	}

	//RETRIEVE - Listar todas as Editoras
	@GetMapping("/editoras")
	public ResponseEntity<List<Editora>> listarEditoras() {
		LOG.info("Listando todos os Editoras disponiveis");
		List<Editora> editoras = editoraService.listarEditoras();

		if (editoras == null || editoras.isEmpty()){
			LOG.info("Nenhuma Editora encontrada.");
			return new ResponseEntity<List<Editora>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Editora>>(editoras, HttpStatus.OK);
	}

	//UPDATE - Atualizar Editora
	@PutMapping("/editoras/{id}")
	public ResponseEntity<Editora> update(@PathVariable long id, @RequestBody Editora editora){
		LOG.info("Atualizando Editora: {}", editora);
		Editora editoraAtual = editoraService.selecionarEditoraPorId(id);

		if (editoraAtual == null){
			LOG.info("Editora com o ID: {} nao encontrado", id);
			return new ResponseEntity<Editora>(HttpStatus.NOT_FOUND);
		}

		editoraAtual.setId(editora.getId());
		editoraAtual.setNomeEditora(editora.getNomeEditora());
		editoraAtual.setSite(editora.getSite());

		editoraService.atualizarEditora(editora);

		return new ResponseEntity<Editora>(editoraAtual, HttpStatus.OK);
	}

	//DELETE - Remover Editora
	@DeleteMapping("/editoras/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id){
		LOG.info("Removendo Editora com o ID: {}", id);
		Editora editora = editoraService.selecionarEditoraPorId(id);

		if (editora == null){
			LOG.info("Nao foi possivel remover Editora. ID: {} nao encontrado.", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		editoraService.removerEditora(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
