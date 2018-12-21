package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.model.Editora;

public interface IEditoraService {

	void adicionarEditora(Editora editora);					//Create
	List<Editora> listarEditoras();							//Retrieve
    Editora selecionarEditoraPorId(long id);				//Retrieve
    void atualizarEditora(Editora editora);					//Update
    void removerEditora(long id);							//Delete
    
    boolean exists(Editora editora);
	
}
