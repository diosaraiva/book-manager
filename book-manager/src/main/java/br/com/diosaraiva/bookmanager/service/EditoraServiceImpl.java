package br.com.diosaraiva.bookmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diosaraiva.bookmanager.entity.Editora;
import br.com.diosaraiva.bookmanager.repository.IEditoraRepository;

@Service
public class EditoraServiceImpl implements IEditoraService {

	@Autowired
	private IEditoraRepository editoraRepository;
	
	@Override
	public void adicionarEditora(Editora editora) {

		Optional<Editora> editoraConsultada = editoraRepository.findById(editora.getId()); 	

		System.out.println(editoraConsultada);
		
		if (editoraConsultada != null) {
			
			editoraRepository.save(editora);
			
		}
		
	}

	@Override
	public List<Editora> listarEditoras() {

		List<Editora> editoras = new ArrayList<>();
		editoraRepository.findAll().forEach(e -> editoras.add(e));
		
		return editoras;
		
	}

	@Override
	public Editora selecionarEditoraPorId(long idEditora) {

		Editora editora = editoraRepository.findById(idEditora).get();
		return editora;
		
	}

	@Override
	public void atualizarEditora(Editora editora) {

		editoraRepository.save(editora);
		
	}

	@Override
	public void removerEditora(long idEditora) {

		editoraRepository.delete(selecionarEditoraPorId(idEditora));
		
	}

	@Override
	public boolean exists(Editora editora) {

		return selecionarEditoraPorId(editora.getId()) != null;
		
	}

}
