package br.com.diosaraiva.bookmanager.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.diosaraiva.bookmanager.entity.Autor;
import br.com.diosaraiva.bookmanager.entity.Critica;
import br.com.diosaraiva.bookmanager.entity.Editora;
import br.com.diosaraiva.bookmanager.entity.Livro;
import br.com.diosaraiva.bookmanager.entity.LivroExtenso;

public class LivroUtil {

	public static List<LivroExtenso> ConverteListaLivroParaListaLivroExtenso (List<Livro> listaLivros) {

		List<LivroExtenso> listaLivroExtenso = new ArrayList<>();

		for (Livro livro : listaLivros) {
			listaLivroExtenso.add(new LivroExtenso(livro));
		}

		return listaLivroExtenso;
	}

	//Cria objetos ilimitados para Testes
	public static Livro criarLivroTeste(Integer num) {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico " +num.toString()+"a", (int)ValorUtil.criarValorAleatorio(0.00,5.00,2), "Texto " +num.toString()));
		criticas.add(new Critica("Critico " +num.toString()+"b", (int)ValorUtil.criarValorAleatorio(0.00,5.00,2), "Texto " +num.toString()));

		Editora editora = new Editora("Editora " +num.toString(), "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Autor " +num.toString()+"a", "Brasileiro"));
		autores.add(new Autor("Autor " +num.toString()+"b", "Brasileiro"));

		return new Livro(num, StringUtil.gerarTituloLivro(), new Date(), ValorUtil.criarValorAleatorio(0.00,1300.00,2), "Sinopse " +num.toString(),
				autores, editora, criticas);
	}
	
	//Cria lista de objetos ilimitados para Testes
	public static List<Livro> criarListaLivroTeste(int num){
		
		List<Livro> listaLivro = new ArrayList<>();
		
		for (int i = 1; i <= num; i++) {
			listaLivro.add(criarLivroTeste(i));
		}
		
		return listaLivro;
	}

}
