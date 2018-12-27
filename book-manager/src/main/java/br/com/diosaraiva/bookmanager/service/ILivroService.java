package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.entity.Livro;
import br.com.diosaraiva.bookmanager.entity.LivroExtenso;

public interface ILivroService {
	
	void adicionarLivro(Livro livro);								//Create
	void adicionarListaLivros(List<Livro> listaLivro);				//Create
	
	List<Livro> listarLivros();										//Retrieve
    Livro selecionarLivroPorISBN(long isbn);						//Retrieve
    
    void atualizarLivro(Livro livro);								//Update
    
    void removerLivro(long isbn);									//Delete
    
    boolean exists(Livro livro);
    
    List<LivroExtenso> listarLivrosExtensoPorAutor(long idAutor);	//Requisito 009
    
    LivroExtenso selecionarLivroExtensoPorISBN(long isbn);			//Requisito 012
	List<LivroExtenso> listarLivrosExtenso();						//Requisito 012
	
}
