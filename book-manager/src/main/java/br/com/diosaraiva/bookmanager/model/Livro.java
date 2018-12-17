package br.com.diosaraiva.bookmanager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {

    @Id
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

}