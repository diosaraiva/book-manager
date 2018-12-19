package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.diosaraiva.bookmanager.utils.ValorPorExtenso;
import br.com.diosaraiva.bookmanager.model.entity.Livro;
import br.com.diosaraiva.bookmanager.model.repository.LivroRepository;

public class LivroVM {
	
	@Autowired
	private LivroRepository livroRepository;

	private boolean editavel;
	
	public List<Livro> getLivros() {
		return livroRepository.findAll();
	}
	public void setLivros(List<Livro> livros) {
		livroRepository.saveAll(livros);
	}
	public Optional<Livro> getLivro(long isbn) {
		return livroRepository.findById(isbn);
	}
	public void setLivro(Livro livro) {
		livroRepository.save(livro);
	}
	public boolean isEditavel() {
		return editavel;
	}
	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

	public String valorPorExtenso (double valor) {
		return ValorPorExtenso.valorPorExtenso(valor);
	}
}