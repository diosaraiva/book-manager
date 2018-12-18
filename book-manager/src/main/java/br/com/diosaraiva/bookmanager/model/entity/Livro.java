package br.com.diosaraiva.bookmanager.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    
    //Many-to-many Autor
    @ManyToMany
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_isbn"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;
    
    //Many-to-one Editora
    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;
    
    //One-to-many Critica
    @OneToMany
    @JoinColumn(name = "critica_id")
    private List<Critica> criticas;
    
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

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Critica> getCriticas() {
		return criticas;
	}

	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}
    
}