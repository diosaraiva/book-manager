package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.entity.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>  {
	
}