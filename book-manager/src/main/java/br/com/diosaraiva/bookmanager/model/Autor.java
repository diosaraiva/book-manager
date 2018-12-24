package br.com.diosaraiva.bookmanager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "autor")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "id")
public class Autor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nomeAutor;
	private String nacionalidade;
	private Date nascimento;

	//Many-to-many Autor Bidirecional
	@ManyToMany(mappedBy="autores")
	Set<Livro> livros;

	//Construtores
	public Autor() {
	}

	public Autor(String nomeAutor, String nacionalidade, Date nascimento, 
			Set<Livro> livros) {
		this.nomeAutor = nomeAutor;
		this.nacionalidade = nacionalidade;
		this.nascimento = nascimento;
		this.livros = livros;
	}

	public Autor(long id, String nomeAutor, String nacionalidade, Date nascimento, 
			Set<Livro> livros) {
		this.id = id;
		this.nomeAutor = nomeAutor;
		this.nacionalidade = nacionalidade;
		this.nascimento = nascimento;
		this.livros = livros;
	}

	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}

	//Overrides

}