package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.entity.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long>  {
	
}