package br.com.diosaraiva.bookmanager.service;

import java.util.List;

import br.com.diosaraiva.bookmanager.entity.Critica;

public interface ICriticaService {

	void adicionarCritica(Critica critica);

	List<Critica> listarCriticas();
	Critica selecionarCriticaPorId(long idCritica);

	void atualizarCritica(Critica critica);
	void atualizarListaCriticas(List<Critica> criticas);

	void removerCritica(long idCritica);

	boolean exists(Critica critica);

}