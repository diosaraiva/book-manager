package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Autor;

public class AutorVM {

	private List<Autor> autores;
	private Autor autor;
	private boolean editavel;
	
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public boolean isEditavel() {
		return editavel;
	}
	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}
	
}
