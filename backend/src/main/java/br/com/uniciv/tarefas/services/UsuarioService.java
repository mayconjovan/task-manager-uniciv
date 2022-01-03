package br.com.uniciv.tarefas.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario getUsuarioPorId(Integer id) {
		return repo.findById(id).orElseThrow(()-> new EntityNotFoundException());
	}
}
