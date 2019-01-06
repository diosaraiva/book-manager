package br.com.diosaraiva.bookmanager.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Editora implements Serializable {

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
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Editora editora = (Editora) o;

		if (id != editora.id) return false;

		return true;
	}
	
	@Override
	public int hashCode() {
		long result = id;
		result = 31 * result 
				+ (id != null ? id.hashCode() : 0)
				+ (nomeEditora != null ? nomeEditora.hashCode() : 0)
				+ (site != null ? site.hashCode() : 0);

		return (int)result;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)

		.append("id", this.getId())
		.append("nomeEditora", this.getNomeEditora())
		.append("site", this.getSite())

		.toString();
	}
}