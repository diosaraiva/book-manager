package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.model.entity.Critica;

public interface CriticaRepository extends CrudRepository<Critica, Long>  {
	
}