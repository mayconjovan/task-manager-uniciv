package br.com.uniciv.tarefas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.assembler.UsuarioModelAssembler;
import br.com.uniciv.tarefas.controller.request.UsuarioRequest;
import br.com.uniciv.tarefas.controller.response.UsuarioResponse;
import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioModelAssembler assembler;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/{id}")
	public EntityModel<UsuarioResponse> umUsuario(@PathVariable Integer id) {
		Usuario usuario = service.getUsuarioPorId(id);
		EntityModel<UsuarioResponse> usuarioResponse = assembler.toModel(usuario);
		return usuarioResponse;

	}

	@GetMapping
	public CollectionModel<EntityModel<UsuarioResponse>> todosUsuarios() {
		List<Usuario> usuarios = service.getTodosUsuarios();

		List<EntityModel<UsuarioResponse>> usuariosModel = usuarios.stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(usuariosModel,
				linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withSelfRel());
	}

	@PostMapping
	public ResponseEntity<EntityModel<UsuarioResponse>> salvarUsuario(@Valid @RequestBody UsuarioRequest usuarioReq) {

		Usuario usuario = mapper.map(usuarioReq, Usuario.class);
		Usuario usuarioSalvo = service.salvar(usuario);
		EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioSalvo);

		return ResponseEntity.created(usuarioModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(usuarioModel);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<UsuarioResponse>> atualizarUsuario(@PathVariable Integer id,
			@Valid @RequestBody UsuarioRequest usuarioReq) {

		Usuario usuario = mapper.map(usuarioReq, Usuario.class);
		Usuario usuarioSalvo = service.atualizar(id, usuario);
		EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioSalvo);

		return ResponseEntity.ok().body(usuarioModel);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirUsuario(@PathVariable Integer id) {
		service.deleteById(id);
	}

}
