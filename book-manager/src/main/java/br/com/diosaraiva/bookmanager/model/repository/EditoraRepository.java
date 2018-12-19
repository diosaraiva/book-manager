package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diosaraiva.bookmanager.model.entity.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long>  {
	
}
