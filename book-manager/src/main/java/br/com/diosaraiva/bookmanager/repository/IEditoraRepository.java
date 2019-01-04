package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.entity.Editora;

public interface IEditoraRepository extends CrudRepository<Editora, Long>  {
	
}