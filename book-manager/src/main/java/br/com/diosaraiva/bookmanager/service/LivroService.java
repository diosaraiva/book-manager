package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.model.LivroExtenso;
import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.repository.LivroRepository;
import br.com.diosaraiva.bookmanager.utils.ValorPorExtenso;

@Service
public class LivroService implements ILivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public void adicionarLivro(Livro livro) {
		Optional<Livro> livros = livroRepository.findById(livro.getIsbn()); 	
		if (livros != null) {
			livroRepository.save(livro);
		}
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
	public List<LivroExtenso> listarLivrosExtenso() {
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(e -> livros.add(e));
		
		List<LivroExtenso> livrosExtenso = new ArrayList<>();
		
		for (Livro livro : livros) {
			livrosExtenso.add(new LivroExtenso(livro));
		}
		
		return livrosExtenso;
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
		
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(e -> livros.add(e));
		
		List<Livro> listaLivros = new ArrayList<>();
		
		for (Livro livro : livros) {
			for (Autor autor : livro.getAutores()) {
				if(autor.getId() == id_autor) listaLivros.add(livro);
			}
		}
		
		return listaLivros;
	}

	//Requisito 012: OK
	public String valorPorExtenso(double valor) {
		return valorPorExtenso(valor);
	}

	@Override
	public boolean exists(Livro livro) {
		return selecionarLivroPorISBN(livro.getIsbn()) != null;
	}

}