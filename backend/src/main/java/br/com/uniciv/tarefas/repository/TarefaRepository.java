package br.com.uniciv.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniciv.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{

}
