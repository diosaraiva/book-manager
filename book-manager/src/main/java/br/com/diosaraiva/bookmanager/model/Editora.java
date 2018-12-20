package br.com.diosaraiva.bookmanager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editora")
public class Editora implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nomeEditora;
	private String site;

	//Construtores
	public Editora() {
	}

	public Editora(String nomeEditora, String site) {
		this.nomeEditora = nomeEditora;
		this.site = site;
	}

	public Editora(long id, String nomeEditora, String site) {
		this.id = id;
		this.nomeEditora = nomeEditora;
		this.site = site;
	}

	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	//Overrides
	
}