package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long>  {
	
}