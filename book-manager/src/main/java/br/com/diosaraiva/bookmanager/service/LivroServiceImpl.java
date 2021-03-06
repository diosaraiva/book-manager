package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.entity.Autor;
import br.com.diosaraiva.bookmanager.entity.Livro;
import br.com.diosaraiva.bookmanager.entity.LivroExtenso;
import br.com.diosaraiva.bookmanager.repository.ILivroRepository;
import br.com.diosaraiva.bookmanager.utils.LivroUtil;

@Service
public class LivroServiceImpl implements ILivroService {

	@Autowired
	private ILivroRepository livroRepository;

	@Override
	public void adicionarLivro(Livro livro) {

		Optional<Livro> livroConsultado = livroRepository.findById(livro.getIsbn()); 	

		if (livroConsultado != null) {
			livroRepository.save(livro);
		}
	}

	@Override
	public void adicionarListaLivros(List<Livro> listaLivro) {

		for (Livro livro : listaLivro) {
			adicionarLivro(livro);
		}

	}

	@Override
	public Livro selecionarLivroPorISBN(long isbn) {

		Livro livro = livroRepository.findById(isbn).get();
		return livro;

	}

	@Override
	public LivroExtenso selecionarLivroExtensoPorISBN(long isbn) {

		LivroExtenso livroExtenso = new LivroExtenso(livroRepository.findById(isbn).get());

		return livroExtenso;
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
	public List<LivroExtenso> listarLivrosExtensoPorAutor(long idAutor) {

		//Warning
		if (idAutor == 0) {
			return listarLivrosExtenso();
		}
		
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(e -> livros.add(e));

		List<Livro> listaLivros = new ArrayList<>();

		for (Livro livro : livros) {
			for (Autor autor : livro.getAutores()) {
				if(autor.getId() == idAutor) listaLivros.add(livro);
			}
		}

		return LivroUtil.ConverteListaLivroParaListaLivroExtenso(listaLivros);
	}

	@Override
	public boolean exists(Livro livro) {

		return selecionarLivroPorISBN(livro.getIsbn()) != null;

	}

}