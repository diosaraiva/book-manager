package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.entity.Editora;

public class EditoraVM {

	private List<Editora> editoras;
	private Editora editora;
	private boolean editavel;
	
	public List<Editora> getEditoras() {
		return editoras;
	}
	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public boolean isEditavel() {
		return editavel;
	}
	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}
	
}
