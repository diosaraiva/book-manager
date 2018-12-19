package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.repository.LivroRepository;

@Service
public class LivroVMImpl implements ILivroVM {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public List<Livro> listarLivros() {
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(e -> livros.add(e));
		return livros;
	}

	@Override
	public Livro selecionarLivroPorISBN(long isbn) {
		Livro obj = livroRepository.findById(isbn).get();
		return obj;
	}

	@Override
	public boolean adicionarLivro(Livro livro) {
		List<Livro> livros = livroRepository.findByIsbn(livro.getIsbn()); 	
		if (livros.size() > 0) {
			return false;
		} else {
			livroRepository.save(livro);
		}
		return true;
	}

	@Override
	public void atualizarLivro(Livro livro) {
		livroRepository.save(livro);
	}

	@Override
	public void removerLivro(long isbn) {
		livroRepository.delete(selecionarLivroPorISBN(isbn));
	}

}