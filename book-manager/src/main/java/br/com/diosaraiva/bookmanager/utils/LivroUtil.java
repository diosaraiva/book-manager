package br.com.diosaraiva.bookmanager.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.model.Critica;
import br.com.diosaraiva.bookmanager.model.Editora;
import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.model.LivroExtenso;

public class LivroUtil {

	public static List<LivroExtenso> ConverteListaLivroParaListaLivroExtenso (List<Livro> listaLivros) {

		List<LivroExtenso> listaLivroExtenso = new ArrayList<>();

		for (Livro livro : listaLivros) {
			listaLivroExtenso.add(new LivroExtenso(livro));
		}

		return listaLivroExtenso;
	}

	//Cria lista objetos para Testes
	public static List<Livro> criarCenarioTesteLista() {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico 1", 5, "Texto 1"));
		criticas.add(new Critica("Critico 2", 0, "Texto 2"));

		Editora editora = new Editora("Editora 1", "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Diogo 1", "Brasileiro", new Date(), null));
		autores.add(new Autor("Autor 2", "Brasileiro", new Date(), null));

		Set<Livro> livros = new HashSet<Livro>();
		livros.add(new Livro(1, "Titulo 1", new Date(), 10.00, "Sinopse 1", autores, 
				editora, criticas));

		List<Livro> listaLivros = new ArrayList<Livro>();
		listaLivros.addAll(livros);

		return listaLivros;
	}

	//Cria objetos para Testes
	public static Livro criarCenarioTesteLivro() {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico 1", 5, "Texto 1"));
		criticas.add(new Critica("Critico 2", 0, "Texto 2"));

		Editora editora = new Editora("Editora 1", "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Diogo 1", "Brasileiro", new Date(), null));
		autores.add(new Autor("Autor 2", "Brasileiro", new Date(), null));

		return new Livro(1, "Titulo 1", new Date(), 10.00, "Sinopse 1", autores, 
				editora, criticas);
	}

}
