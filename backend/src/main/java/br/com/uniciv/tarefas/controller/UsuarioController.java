package br.com.uniciv.tarefas.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.response.UsuarioResponse;
import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/{id}")
	public UsuarioResponse umUsuario(@PathVariable Integer id) {
		Usuario usuario = service.getUsuarioPorId(id);
		UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
		return usuarioResponse;
		
	}

}
