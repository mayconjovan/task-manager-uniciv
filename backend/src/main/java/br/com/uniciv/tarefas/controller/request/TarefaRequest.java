package br.com.uniciv.tarefas.controller.request;

import java.time.LocalDate;

public class TarefaRequest {

	private Integer id;
	private String descricao;
	private LocalDate dataEntrega;
	private Integer categoriaId;
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
