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
	public static List<Livro> criarCenarioTesteListaLivro() {

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

	//Cria objetos ilimitados para Testes
	public static Livro criarCenarioLivroTeste(Integer num) {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico " +num.toString(), 5, "Texto " +num.toString()));
		criticas.add(new Critica("Critico " +num.toString(), 0, "Texto " +num.toString()));

		Editora editora = new Editora("Editora " +num.toString(), "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Autor " +num.toString(), "Brasileiro", new Date(), null));
		autores.add(new Autor("Autor " +num.toString(), "Brasileiro", new Date(), null));

		return new Livro(num, "Titulo " +num.toString(), new Date(), 10.00, "Sinopse " +num.toString(),
				autores, editora, criticas);
	}
	
	//Cria lista de objetos ilimitados para Testes
	public static List<Livro> criarCenarioListaLivroTeste(int num){
		
		List<Livro> listaLivro = new ArrayList<>();
		
		for (int i = 1; i <= num+1; i++) {
			listaLivro.add(criarCenarioLivroTeste(i));
		}
		
		return listaLivro;
	}

}
