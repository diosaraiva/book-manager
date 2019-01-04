package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.entity.Autor;
import br.com.diosaraiva.bookmanager.repository.IAutorRepository;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepository autorRepository;
	
	@Override
	public void adicionarAutor(Autor autor) {

		Optional<Autor> autorConsultado = autorRepository.findById(autor.getId()); 	

		if (autorConsultado != null) {
			autorRepository.save(autor);
		}
		
	}

	@Override
	public List<Autor> listarAutores() {

		List<Autor> autores = new ArrayList<>();
		autorRepository.findAll().forEach(e -> autores.add(e));
		
		return autores;
	}

	@Override
	public Autor selecionarAutorPorId(long idAutor) {

		Autor autor = autorRepository.findById(idAutor).get();
		return autor;
		
	}

	@Override
	public void atualizarAutor(Autor autor) {

		autorRepository.save(autor);
		
	}

	@Override
	public void atualizarListaAutores(List<Autor> autores) {

		for (Autor autor : autores) {
			atualizarAutor(autor);
		}
		
	}

	@Override
	public void removerAutor(long idAutor) {

		autorRepository.delete(selecionarAutorPorId(idAutor));
		
	}

	@Override
	public boolean exists(Autor autor) {

		return selecionarAutorPorId(autor.getId()) != null;
		
	}

}
