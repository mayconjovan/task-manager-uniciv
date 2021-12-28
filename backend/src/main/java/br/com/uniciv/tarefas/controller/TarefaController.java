package br.com.uniciv.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.repository.TarefaRepository;

@RestController
public class TarefaController {

	
	@Autowired
	private TarefaRepository repository;
	
	@GetMapping("/tarefas")
	public List<Tarefa> todasTaferas() {
		return repository.findAll();
	}
	
	@GetMapping("/tarefas/{id}")
	public Tarefa getTarefa(@PathVariable Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	
	@PostMapping("/tarefas")
	public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	@DeleteMapping("/tarefas/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
