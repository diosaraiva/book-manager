package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Critica;

public interface ICriticaService {

	void adicionarCritica(Critica critica);				//Create
	List<Critica> listarCriticas();						//Retrieve
    Critica selecionarCriticaPorId(long id);			//Retrieve
    void atualizarCritica(Critica critica);				//Update
    void removerCritica(long id);						//Delete
    
    boolean exists(Critica critica);
	
}
