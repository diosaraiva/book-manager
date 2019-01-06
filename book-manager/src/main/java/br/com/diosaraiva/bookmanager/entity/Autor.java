package br.com.diosaraiva.bookmanager.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nomeAutor;
	private String nacionalidade;

	//Many to Many Livro
	//Referencia: https://stackoverflow.com/questions/42394095/many-to-many-relationship-between-two-entities-in-spring-boot
	@JsonIgnore
	@ManyToMany(mappedBy = "autores")
	private Set<Livro> livros;

	//Construtores
	public Autor() {
	}

	public Autor(String nomeAutor, String nacionalidade) {
		this.nomeAutor = nomeAutor;
		this.nacionalidade = nacionalidade;
	}

	public Autor(long id, String nomeAutor, String nacionalidade) {
		this.id = id;
		this.nomeAutor = nomeAutor;
		this.nacionalidade = nacionalidade;
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

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}

	//Overrides
	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId())
				.append("nomeAutor", this.getNomeAutor())
				.append("nacionalidade", this.getNacionalidade())

				.toString();
	}
}