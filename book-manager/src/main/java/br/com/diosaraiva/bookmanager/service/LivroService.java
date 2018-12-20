package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.repository.LivroRepository;

@Service
public class LivroService implements ILivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public boolean adicionarLivro(Livro livro) {
		Optional<Livro> livros = livroRepository.findById(livro.getIsbn()); 	
		if (livros == null) {
			return false;
		} else {
			livroRepository.save(livro);
		}
		return true;
	}
	
	@Override
	public Livro selecionarLivroPorISBN(long isbn) {
		Livro obj = livroRepository.findById(isbn).get();
		return obj;
	}
	
	@Override
	public List<Livro> listarLivros() {
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(e -> livros.add(e));
		return livros;
	}

	@Override
	public void atualizarLivro(Livro livro) {
		livroRepository.save(livro);
	}

	@Override
	public void removerLivro(long isbn) {
		livroRepository.delete(selecionarLivroPorISBN(isbn));
	}

	//Requisito 009: OK
	@Override
	public List<Livro> listarLivrosPorAutor(long id_autor) {
		//TODO
		return null;
	}

}