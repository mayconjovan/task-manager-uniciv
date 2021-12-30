package br.com.uniciv.tarefas.controller.request;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TarefaRequest {

	private Integer id;

	@Size(min = 5, max = 150, message = "tarefa.descricao.size")
	@NotBlank(message = "{tarefa.descricao.not-blank}")
	private String descricao;
	
	@FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
	private LocalDate dataEntrega;
	
	@NotNull(message = "{tarefa.categoria.not-null}")
	@Min(value=1, message="{tarefa.categoria.min}")
	private Integer categoriaId;
	
	@NotNull(message = "{tarefa.usuario.not-null}")
	@Min(value=1, message="{tarefa.usuario.min}")
	private Integer usuarioID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}

}
