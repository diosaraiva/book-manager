package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diosaraiva.bookmanager.model.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>  {
	
}