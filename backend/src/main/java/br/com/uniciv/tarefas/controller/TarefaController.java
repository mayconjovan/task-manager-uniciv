package br.com.uniciv.tarefas.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.services.TarefaService;

@RestController
public class TarefaController {

	@Autowired
	private TarefaService service;

	@GetMapping("/tarefas")
	public List<Tarefa> todasTaferas(@RequestParam Map<String, String> parametros) {
		if (parametros.isEmpty())
			return service.getTodasTarefas();

		String descricao = parametros.get("descricao");
		return service.getTarefasByDescricao("%" + descricao + "%");
	}

	@GetMapping("/tarefas/{id}")
	public Tarefa getTarefa(@PathVariable Integer id) {
		return service.getTarefaPorId(id);
	}

	@PostMapping("/tarefas")
	public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
		return service.salvarTarefa(tarefa);
	}

	@DeleteMapping("/tarefas/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
