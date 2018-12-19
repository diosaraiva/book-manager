package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diosaraiva.bookmanager.model.entity.Critica;

public interface CriticaRepository extends JpaRepository<Critica, Long>  {
	
}