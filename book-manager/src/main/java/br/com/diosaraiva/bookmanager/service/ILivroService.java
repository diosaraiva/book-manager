package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.entity.Livro;
import br.com.diosaraiva.bookmanager.entity.LivroExtenso;

public interface ILivroService {

	void adicionarLivro(Livro livro);
	void adicionarListaLivros(List<Livro> listaLivro);

	LivroExtenso selecionarLivroExtensoPorISBN(long isbn);
	List<LivroExtenso> listarLivrosExtensoPorAutor(long idAutor);
	List<LivroExtenso> listarLivrosExtenso();

	void atualizarLivro(Livro livro);

	void removerLivro(long isbn);

	boolean exists(Livro livro);

	//Deprecados
	List<Livro> listarLivros();
	Livro selecionarLivroPorISBN(long isbn);

}
