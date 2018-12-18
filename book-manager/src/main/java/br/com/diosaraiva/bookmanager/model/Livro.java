package br.com.diosaraiva.bookmanager.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO) //--> Deve ser fornecido pelo Usuário cadastrante.
    private Long isbn;
    private String titulo;
    private Date dataPublicacao;
    private double preco;
    private String sinopse;
    
    protected Livro() {}

    public Livro(Long isbn, String titulo, Date dataPublicacao, double preco, String sinopse) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.preco = preco;
        this.sinopse = sinopse;
    }

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
    
}