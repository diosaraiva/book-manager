package br.com.diosaraiva.bookmanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diosaraiva.bookmanager.model.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>  {
	
}