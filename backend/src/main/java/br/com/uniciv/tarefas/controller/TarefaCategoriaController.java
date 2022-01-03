package br.com.uniciv.tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.request.TarefaCategoriaRequest;
import br.com.uniciv.tarefas.controller.response.TarefaCategoriaResponse;
import br.com.uniciv.tarefas.model.TarefaCategoria;
import br.com.uniciv.tarefas.services.TarefaCategoriaService;

@RestController
@RequestMapping("/categorias")
public class TarefaCategoriaController {

	@Autowired
	private TarefaCategoriaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<TarefaCategoriaResponse> todasCategorias() {
		List<TarefaCategoria> categorias = service.getTodasCategorias();
		return categorias.stream().map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public TarefaCategoriaResponse umaCategoria(@PathVariable Integer id) {
		return mapper.map(service.getCategoriaPorId(id), TarefaCategoriaResponse.class);
	}

	@PostMapping
	public TarefaCategoriaResponse salvarCategoria(@Valid @RequestBody TarefaCategoriaRequest categoriaReq) {
		TarefaCategoria categoria = mapper.map(categoriaReq, TarefaCategoria.class);

		return mapper.map(service.salvarCategoria(categoria), TarefaCategoriaResponse.class);
	}

	@DeleteMapping("/{id}")
	public void excluirCategoria(@PathVariable Integer id) {
		service.deleteCategoriaById(id);
	}

}
