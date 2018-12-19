package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Livro;

public interface ILivroVM {
	
	List<Livro> listarLivros();
    Livro selecionarLivroPorISBN(long isbn);
    boolean adicionarLivro(Livro livro);
    void atualizarLivro(Livro livro);
    void removerLivro(long isbn);
    
}
