package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Livro;

public interface ILivroService {
	
	void adicionarLivro(Livro livro);					//Create
	List<Livro> listarLivros();							//Retrieve
    Livro selecionarLivroPorISBN(long isbn);			//Retrieve
    void atualizarLivro(Livro livro);					//Update
    void removerLivro(long isbn);						//Delete
    
    boolean exists(Livro livro);
    
    List<Livro> listarLivrosPorAutor(long id_autor);	//Requisito 009
}
