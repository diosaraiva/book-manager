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
public class Critica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nomeCritico;
	private int nota;
	private String texto;

	//Construtores
	public Critica() {
	}

	public Critica(String nomeCritico, int nota, String texto) {
		this.nomeCritico = nomeCritico;
		this.nota = nota;
		this.texto = texto;
	}

	public Critica(long id, String nomeCritico, int nota, String texto) {
		this.id = id;
		this.nomeCritico = nomeCritico;
		this.nota = nota;
		this.texto = texto;
	}

	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCritico() {
		return nomeCritico;
	}
	public void setNomeCritico(String nomeCritico) {
		this.nomeCritico = nomeCritico;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	//Overrides
	@Override
	public String toString() {
		return new ToStringCreator(this)

		.append("id", this.getId())
		.append("nomeCritico", this.getNomeCritico())
		.append("nota", this.getNota())
		.append("texto", this.getTexto())

		.toString();
	}
}