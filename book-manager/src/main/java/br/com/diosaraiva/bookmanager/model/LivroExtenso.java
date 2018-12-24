package br.com.diosaraiva.bookmanager.model;

import br.com.diosaraiva.bookmanager.utils.ValorPorExtenso;

//Este Model é usado para fornecer objetos Livro com a String valorPorExtenso. 
//A String valorPorExtenso não é persistida no banco de dados (somente RETRIEVE).
public class LivroExtenso extends Livro {

	private static final long serialVersionUID = 7242794978474869804L;
	
	@SuppressWarnings("unused")
	private String valorPorExtenso; 

	//Construtores
	public LivroExtenso() {
	}

	//Recebe Livro e estende com String valorPorExtenso, que não é persistida.
	public LivroExtenso(Livro livro) {
		this.setIsbn(livro.getIsbn());
		this.setTitulo(livro.getTitulo());
		this.setDataPublicacao(livro.getDataPublicacao());
		this.setPreco(livro.getPreco());
		this.setSinopse(livro.getSinopse());
		this.setAutores(livro.getAutores());
		this.setEditora(livro.getEditora());
		this.setCriticas(livro.getCriticas());
		
		//Requisito 012: OK
		this.valorPorExtenso = ValorPorExtenso.valorPorExtenso(livro.getPreco());
	}

}
