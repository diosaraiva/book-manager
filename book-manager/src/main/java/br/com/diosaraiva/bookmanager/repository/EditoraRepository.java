package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.Editora;

public interface EditoraRepository extends CrudRepository<Editora, Long>  {
	
}
