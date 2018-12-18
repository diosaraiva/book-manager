package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.entity.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>  {
	
}