package br.com.diosaraiva.bookmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.service.IEditoraService;

@RestController
@RequestMapping(value= "/editoras")
public class EditoraController {

	private final Logger LOG = LoggerFactory.getLogger(EditoraController.class);

	@Autowired
	private IEditoraService editoraService;
	
	//
	
}
