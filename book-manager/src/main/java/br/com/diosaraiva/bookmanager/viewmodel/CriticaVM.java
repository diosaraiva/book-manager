package br.com.diosaraiva.bookmanager.viewmodel;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.entity.Critica;

public class CriticaVM {

	private List<Critica> criticas;
	private Critica critica;
	private boolean editavel;
	
	public List<Critica> getCriticas() {
		return criticas;
	}
	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}
	public Critica getCritica() {
		return critica;
	}
	public void setCritica(Critica critica) {
		this.critica = critica;
	}
	public boolean isEditavel() {
		return editavel;
	}
	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}
	
}
