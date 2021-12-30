package br.com.uniciv.tarefas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.uniciv.tarefas.exception.TarefaStatusException;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.model.TarefaStatus;
import br.com.uniciv.tarefas.repository.TarefaRepository;
import br.com.uniciv.tarefas.services.TarefaService;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {
	
	@Mock
	private TarefaRepository repo;
	
	@InjectMocks
	private TarefaService service;
	
	@Test
	void naoDeveConcluirTarefaCancelada() {
		
		Integer idExemplo = 1;
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setStatus(TarefaStatus.CANCELADA);
		
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(idExemplo));
		
	}
	
	@Test
	void naoDeveCancelarTarefaConcluida() {
		Integer idExemplo = 1;
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, ()-> service.cancelarTarefa(idExemplo));
		
	}

}
