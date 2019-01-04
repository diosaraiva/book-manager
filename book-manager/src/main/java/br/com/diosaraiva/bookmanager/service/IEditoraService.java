package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.entity.Editora;

public interface IEditoraService {

	void adicionarEditora(Editora editora);

	List<Editora> listarEditoras();
	Editora selecionarEditoraPorId(long idEditora);

	void atualizarEditora(Editora editora);

	void removerEditora(long idEditora);

	boolean exists(Editora editora);
    
}