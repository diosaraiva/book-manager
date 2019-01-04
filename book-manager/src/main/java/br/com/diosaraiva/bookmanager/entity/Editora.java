package br.com.diosaraiva.bookmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Editora {

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