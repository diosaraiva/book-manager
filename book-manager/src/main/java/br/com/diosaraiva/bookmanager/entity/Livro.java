package br.com.diosaraiva.bookmanager.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Livro {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) --> Deve ser fornecido pelo Usuário cadastrante.
	private Long isbn;
	private String titulo;
	private Date dataPublicacao;
	private double preco;
	private String sinopse;

	//Many to Many Autor
	//Referencia: https://stackoverflow.com/questions/42394095/many-to-many-relationship-between-two-entities-in-spring-boot
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="livro_autor")
	private Set<Autor> autores;

	//Many-to-one Editora
	//Referencia: https://stackoverflow.com/questions/49158025/spring-boot-how-to-save-many-to-one-entities-to-database
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Editora editora;

	//One-to-many Critica
	//Referencia: https://stackoverflow.com/questions/49158025/spring-boot-how-to-save-many-to-one-entities-to-database
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private Set<Critica> criticas;

	//Construtores
	public Livro() {
	}

	public Livro(String titulo, Date dataPublicacao, double preco, String sinopse, 
			Set<Autor> autores, Editora editora, Set<Critica> criticas) {
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.preco = preco;
		this.sinopse = sinopse;
		this.autores = autores;
		this.editora = editora;
		this.criticas = criticas;
	}

	public Livro(long isbn, String titulo, Date dataPublicacao, double preco, 
			String sinopse, Set<Autor> autores, Editora editora, Set<Critica> criticas) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.preco = preco;
		this.sinopse = sinopse;
		this.autores = autores;
		this.editora = editora;
		this.criticas = criticas;
	}

	//Getters e Setters
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

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Critica> getCriticas() {
		return criticas;
	}

	public void setCriticas(Set<Critica> criticas) {
		this.criticas = criticas;
	}

	//Overrides
	@Override
	public String toString() {
		String livro = "Livro{" +
				"isbn=" + isbn +
				", titulo='" + titulo +
				", dataPublicacao='" + dataPublicacao + 
				", preco='" + preco +
				", sinopse='" + sinopse;

		for (Autor autor : autores) {
			livro = livro + 
					", autor='" + autor;
		}

		livro = livro + 
				", editora='" + editora;

		for (Critica critica : criticas) {
			livro = livro + 
					", critica='" + critica;
		}

		livro = livro +	'\'' + '}';

		return livro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Livro livro = (Livro) o;

		if (isbn != livro.isbn) return false;

		return true;
	}

	@Override
	public int hashCode() {
		long result = isbn;
		result = 31 * result 
				+ (titulo != null ? titulo.hashCode() : 0)
				+ (dataPublicacao != null ? dataPublicacao.hashCode() : 0)
				+ (preco != 0 ? 1 : 0)
				+ (sinopse != null ? sinopse.hashCode() : 0)
				+ (autores != null ? autores.hashCode() : 0)
				+ (editora != null ? editora.hashCode() : 0)
				+ (criticas != null ? criticas.hashCode() : 0);

		return (int)result;
	}
}