package br.com.uniciv.tarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.exception.TarefaStatusException;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.model.TarefaStatus;
import br.com.uniciv.tarefas.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repo;

	public List<Tarefa> getTodasTarefas() {
		return repo.findAll();
	}

	public List<Tarefa> getTarefasByDescricao(String descricao) {
		return repo.findByDescricaoLike("%" + descricao + "%");
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

	public Tarefa iniciarTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);
		if (!TarefaStatus.ABERTO.equals(tarefa.getStatus()))
			throw new TarefaStatusException();
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);

		return repo.save(tarefa);

	}

	public Tarefa concluirTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);

		if (TarefaStatus.CANCELADA.equals(tarefa.getStatus()))
			throw new TarefaStatusException();

		tarefa.setStatus(TarefaStatus.CONCLUIDA);

		return repo.save(tarefa);

	}

	public Tarefa cancelarTarefa(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);

		if (TarefaStatus.CONCLUIDA.equals(tarefa.getStatus()))
			throw new TarefaStatusException("{tarefa.cancelar.naopermitido}");

		tarefa.setStatus(TarefaStatus.CANCELADA);
		repo.save(tarefa);
		return tarefa;
	}
}
