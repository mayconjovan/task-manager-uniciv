package br.com.uniciv.tarefas.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.request.TarefaRequest;
import br.com.uniciv.tarefas.controller.response.TarefaResponse;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.services.TarefaService;

@RestController
public class TarefaController {

	@Autowired
	private TarefaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/tarefas")
	public List<TarefaResponse> todasTaferas(@RequestParam Map<String, String> parametros) {
		if (parametros.isEmpty()) {
			return convertTarefaResponse(service.getTodasTarefas());
		}
		return convertTarefaResponse(service.getTarefasByDescricao(parametros.get("descricao")));
	}

	@GetMapping("/tarefas/{id}")
	public TarefaResponse getTarefa(@PathVariable Integer id) {	
		return mapper.map(service.getTarefaPorId(id), TarefaResponse.class);	
	}

	@PostMapping("/tarefas")
	public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) {
		Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);
		
		return mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
	}

	@DeleteMapping("/tarefas/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	private List<TarefaResponse> convertTarefaResponse(List<Tarefa> list) {
		return list.stream().map(obj -> mapper.map(obj, TarefaResponse.class)).collect(Collectors.toList());
	}
}
