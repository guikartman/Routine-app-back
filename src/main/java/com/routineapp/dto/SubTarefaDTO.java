package com.routineapp.dto;

import java.io.Serializable;

import com.routineapp.entidades.SubTarefa;

public class SubTarefaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tituloSubTarefa;
	private Boolean executado;

	public SubTarefaDTO() {
	}

	public SubTarefaDTO(SubTarefa subTarefa) {
		this.id = subTarefa.getCdSubTarefa();
		this.tituloSubTarefa = subTarefa.getTituloSubTarefa();
		this.executado = subTarefa.getExecutado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTituloSubTarefa() {
		return tituloSubTarefa;
	}

	public void setTituloSubTarefa(String tituloSubTarefa) {
		this.tituloSubTarefa = tituloSubTarefa;
	}

	public Boolean getExecutado() {
		return executado;
	}

	public void setExecutado(Boolean executado) {
		this.executado = executado;
	}

}
