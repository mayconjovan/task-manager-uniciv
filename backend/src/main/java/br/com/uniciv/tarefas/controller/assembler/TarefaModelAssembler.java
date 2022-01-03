package br.com.uniciv.tarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.uniciv.tarefas.controller.TarefaCategoriaController;
import br.com.uniciv.tarefas.controller.TarefaController;
import br.com.uniciv.tarefas.controller.UsuarioController;
import br.com.uniciv.tarefas.controller.response.TarefaResponse;
import br.com.uniciv.tarefas.model.Tarefa;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

	@Autowired
	private ModelMapper mapper;

	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		TarefaResponse tarefaResp = mapper.map(tarefa, TarefaResponse.class);

		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResp,
				linkTo(methodOn(TarefaController.class).getTarefa(tarefaResp.getId())).withSelfRel(),
				linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas"),
				linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(tarefaResp.getCategoriaId()))
						.withRel("categoria"),
				linkTo(methodOn(UsuarioController.class).umUsuario(tarefaResp.getUsuarioId())).withRel("usuario"));

		return tarefaModel;
	}

}
