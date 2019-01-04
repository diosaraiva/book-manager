package br.com.diosaraiva.bookmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.service.IAutorService;

@RestController
@RequestMapping(value= "/autores")
public class AutorController {

	private final Logger LOG = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private IAutorService autorService;
	
	//
	
}
