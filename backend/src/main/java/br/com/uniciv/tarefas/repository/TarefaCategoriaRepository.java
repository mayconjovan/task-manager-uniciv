package br.com.uniciv.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniciv.tarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer> {

}
