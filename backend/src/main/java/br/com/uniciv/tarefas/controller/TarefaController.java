package br.com.uniciv.tarefas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.assembler.TarefaModelAssembler;
import br.com.uniciv.tarefas.controller.request.TarefaRequest;
import br.com.uniciv.tarefas.controller.response.TarefaResponse;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.services.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TarefaModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<TarefaResponse>> todasTarefas(@RequestParam Map<String, String> parametros) {
		List<Tarefa> tarefas = new ArrayList<>();
		
		if (parametros.isEmpty()) {
			tarefas = service.getTodasTarefas();
		} else {
			String descricao = parametros.get("descricao");
			tarefas = service.getTarefasByDescricao(descricao);
		}
		
		List<EntityModel<TarefaResponse>> tarefasModel = tarefas.stream().map(assembler::toModel).collect(Collectors.toList());
		
		return CollectionModel.of(tarefasModel, 
				linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>()))
				.withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<TarefaResponse> getTarefa(@PathVariable Integer id) {
		Tarefa tarefa = service.getTarefaPorId(id);
		return assembler.toModel(tarefa);

	}

	@PostMapping
	public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) {
		Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);

		return mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
	}

	@DeleteMapping("/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	@PutMapping("/{id}/iniciar")
	public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id){
		Tarefa tarefa = service.iniciarTarefaPorId(id);
		return assembler.toModel(tarefa);
	}
	
	@PutMapping("/{id}/concluir")
	public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Integer id) {
		Tarefa tarefa = service.concluirTarefaPorId(id);
		return assembler.toModel(tarefa);
	}
	
	@PutMapping("/{id}/cancelar")
	public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = service.cancelarTarefa(id);
		return assembler.toModel(tarefa);
	}

}
