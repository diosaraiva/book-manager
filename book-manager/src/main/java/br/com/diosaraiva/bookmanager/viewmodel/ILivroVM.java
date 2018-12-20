package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Livro;

public interface ILivroVM {
	
	boolean adicionarLivro(Livro livro);				//Create
	List<Livro> listarLivros();							//Retrieve
    Livro selecionarLivroPorISBN(long isbn);			//Retrieve
    void atualizarLivro(Livro livro);					//Update
    void removerLivro(long isbn);						//Delete
    
    List<Livro> listarLivrosPorAutor(long id_autor);	//Requisito 009
}
