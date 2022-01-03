package br.com.uniciv.tarefas.services;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.model.TarefaCategoria;
import br.com.uniciv.tarefas.repository.TarefaCategoriaRepository;

@Service
public class TarefaCategoriaService {
	
	@Autowired
	private TarefaCategoriaRepository repo;
	
	public List<TarefaCategoria> getTodasCategorias() {
		return repo.findAll();
	}
	
	public TarefaCategoria getCategoriaPorId(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public TarefaCategoria salvarCategoria(TarefaCategoria categoria) {
		return repo.save(categoria);
	}

	public void deleteCategoriaById(Integer id) {
		repo.deleteById(id);
	}

}
