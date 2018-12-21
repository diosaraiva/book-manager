package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Autor;

public interface IAutorService {

	void adicionarAutor(Autor autor);					//Create
	List<Autor> listarAutores();						//Retrieve
    Autor selecionarAutorPorId(long id);				//Retrieve
    void atualizarAutor(Autor autor);					//Update
    void removerAutor(long id);							//Delete
    
    boolean exists(Autor autor);
	
}
