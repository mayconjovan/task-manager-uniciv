package br.com.uniciv.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniciv.tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
