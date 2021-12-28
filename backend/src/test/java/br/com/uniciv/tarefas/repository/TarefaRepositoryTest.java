package br.com.uniciv.tarefas.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.uniciv.tarefas.model.Tarefa;

@SpringBootTest
public class TarefaRepositoryTest {

	@Autowired
	private TarefaRepository repo;
	
	@Test
	void testFindByNomeCategoria() {
		List<Tarefa> tarefas = repo.findByNomeCategoria("Estudos");
		Assertions.assertEquals(1,  tarefas.size());
	}
	
	@Test
	void testTarefasPorCategorias() {
		List<Tarefa> tarefas = repo.tarefasPorCategoria("Estudos");
		Assertions.assertEquals(1,  tarefas.size());
	}
	
}
