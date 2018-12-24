package br.com.diosaraiva.bookmanager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.diosaraiva.bookmanager.utils.ValorUtil;

//Este Model é usado para fornecer objetos Livro com a String valorPorExtenso. 
//A String valorPorExtenso não é persistida no banco de dados (somente RETRIEVE).
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "isbn")
public class LivroExtenso extends Livro {

	private static final long serialVersionUID = 7242794978474869804L;

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
		this.setValorPorExtenso(ValorUtil.valorPorExtenso(livro.getPreco()));
	}

	public String getValorPorExtenso() {
		return valorPorExtenso;
	}

	public void setValorPorExtenso(String valorPorExtenso) {
		this.valorPorExtenso = valorPorExtenso;
	}

}
