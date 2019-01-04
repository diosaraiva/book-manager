package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.entity.Critica;
import br.com.diosaraiva.bookmanager.repository.ICriticaRepository;

@Service
public class CriticaServiceImpl implements ICriticaService {

	@Autowired
	private ICriticaRepository criticaRepository;
	
	@Override
	public void adicionarCritica(Critica critica) {

		Optional<Critica> criticaConsultada = criticaRepository.findById(critica.getId()); 	

		if (criticaConsultada != null) {
			criticaRepository.save(critica);
		}
		
	}

	@Override
	public List<Critica> listarCriticas() {

		List<Critica> criticas = new ArrayList<>();
		criticaRepository.findAll().forEach(e -> criticas.add(e));
		
		return criticas;
		
	}

	@Override
	public Critica selecionarCriticaPorId(long idCritica) {

		Critica critica = criticaRepository.findById(idCritica).get();
		return critica;
		
	}

	@Override
	public void atualizarCritica(Critica critica) {

		criticaRepository.save(critica);
		
	}

	@Override
	public void atualizarListaCriticas(List<Critica> criticas) {

		for (Critica critica : criticas) {
			atualizarCritica(critica);
		}
		
	}

	@Override
	public void removerCritica(long idCritica) {

		criticaRepository.delete(selecionarCriticaPorId(idCritica));
		
	}

	@Override
	public boolean exists(Critica critica) {

		return selecionarCriticaPorId(critica.getId()) != null;
		
	}

}
