package br.com.diosaraiva.bookmanager.model;

import br.com.diosaraiva.bookmanager.utils.ValorPorExtenso;

public class LivroExtenso extends Livro {

	private static final long serialVersionUID = 7242794978474869804L;
	
	@SuppressWarnings("unused")
	private String valorPorExtenso; 

	//Construtores
	public LivroExtenso() {
	}

	public LivroExtenso(Livro livro) {
		this.setIsbn(livro.getIsbn());
		this.setTitulo(livro.getTitulo());
		this.setDataPublicacao(livro.getDataPublicacao());
		this.setPreco(livro.getPreco());
		this.setSinopse(livro.getSinopse());
		this.setAutores(livro.getAutores());
		this.setEditora(livro.getEditora());
		this.setCriticas(livro.getCriticas());
		
		//Requisito 012 OK
		this.valorPorExtenso = ValorPorExtenso.valorPorExtenso(livro.getPreco());
	}

}
