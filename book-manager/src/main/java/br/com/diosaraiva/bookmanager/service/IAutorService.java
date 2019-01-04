package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.entity.Autor;

public interface IAutorService {

	void adicionarAutor(Autor autor);

	List<Autor> listarAutores();
	Autor selecionarAutorPorId(long idAutor);

	void atualizarAutor(Autor autor);
	void atualizarListaAutores(List<Autor> autores);

	void removerAutor(long idAutor);

	boolean exists(Autor autor);

}