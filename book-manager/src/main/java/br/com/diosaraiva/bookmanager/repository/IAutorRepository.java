package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.entity.Autor;

public interface IAutorRepository extends CrudRepository<Autor, Long>  {
	
}