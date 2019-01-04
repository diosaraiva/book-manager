package br.com.diosaraiva.bookmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diosaraiva.bookmanager.entity.Critica;

public interface ICriticaRepository extends CrudRepository<Critica, Long>  {
	
}