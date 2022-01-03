package br.com.uniciv.tarefas.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TarefaCategoriaRequest {
	
	
	private Integer id;
	
	@Size(min = 5, max = 150, message = "tarefa.categoria.min")
	@NotBlank(message = "{tarefa.categoria.not-null}")
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
