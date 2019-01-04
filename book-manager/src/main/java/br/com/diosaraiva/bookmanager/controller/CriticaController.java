package br.com.diosaraiva.bookmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diosaraiva.bookmanager.service.ICriticaService;

@RestController
@RequestMapping(value= "/criticas")
public class CriticaController {

	private final Logger LOG = LoggerFactory.getLogger(CriticaController.class);

	@Autowired
	private ICriticaService criticaService;
	
	//
	
}
