package br.com.diosaraiva.bookmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>  {
	List<Livro> findByIsbn(Long isbn);
	//List<Livro> findByAutores(List<Autor> autores);
	//List<Livro> findByIsbnAndAutores(long isbn, List<Autor> autores);
}