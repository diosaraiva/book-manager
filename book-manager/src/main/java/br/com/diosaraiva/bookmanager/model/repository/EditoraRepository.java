package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.entity.Editora;

public interface EditoraRepository extends CrudRepository<Editora, Long>  {
	
}
