package br.com.diosaraiva.bookmanager.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "critica")
public class Critica {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nomeCritico;
    private double nota;
    private String texto;

    protected Critica() {}

    public Critica(String nomeCritico, double nota, String texto) {
        this.nomeCritico = nomeCritico;
        this.nota = nota;
        this.texto = texto;
    }

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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}