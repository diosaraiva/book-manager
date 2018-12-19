package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.diosaraiva.bookmanager.model.entity.Autor;
import br.com.diosaraiva.bookmanager.model.repository.AutorRepository;

public class AutorVM {
	
	@Autowired
	private AutorRepository autorRepository;

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
