package br.com.uniciv.tarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repo;

	public List<Tarefa> getTodasTarefas() {
		return repo.findAll();
	}

	public List<Tarefa> getTarefasByDescricao(String descricao) {
		return repo.findByDescricao("%" + descricao + "%");
	}

	public Tarefa getTarefaPorId(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Tarefa salvarTarefa(Tarefa tarefa) {
		return repo.save(tarefa);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
