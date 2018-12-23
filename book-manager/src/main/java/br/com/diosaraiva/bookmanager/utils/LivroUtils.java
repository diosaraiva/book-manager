package br.com.diosaraiva.bookmanager.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.model.LivroExtenso;

public class LivroUtils {
	
	public static List<LivroExtenso> ConverteParaListaExtenso (List<Livro> listaLivros) {

		List<LivroExtenso> listaLivroExtenso = new ArrayList<>();

		for (Livro livro : listaLivros) {
			listaLivroExtenso.add(new LivroExtenso(livro));
		}

		return listaLivroExtenso;
	}
	
}
