package br.com.uniciv.tarefas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.uniciv.tarefas.exception.TarefaStatusException;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.model.TarefaStatus;
import br.com.uniciv.tarefas.services.TarefaService;

@SpringBootTest
public class TarefaServiceIntegrationTest {

	@Autowired
	private TarefaService service;

	@Test
	void deveIniciarTarefa() {
		Tarefa tarefa = service.iniciarTarefaPorId(3);
		System.out.println(tarefa);

		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
	}

	@Test
	void naoDeveIniciarTarefaConcluida() {
		Tarefa tarefa = service.getTarefaPorId(3);
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		service.salvarTarefa(tarefa);

		Assertions.assertThrows(TarefaStatusException.class, () -> service.iniciarTarefaPorId(3));
	}
}
