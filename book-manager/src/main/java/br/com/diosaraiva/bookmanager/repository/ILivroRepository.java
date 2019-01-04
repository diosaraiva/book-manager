package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.entity.Livro;

public interface ILivroRepository extends CrudRepository<Livro, Long>  {
	
}