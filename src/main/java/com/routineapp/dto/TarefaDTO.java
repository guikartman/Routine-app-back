package com.routineapp.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.routineapp.entidades.Tarefa;

public class TarefaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private Boolean executado;
	private LocalDate data;

	public TarefaDTO() {
	}

	public TarefaDTO(Tarefa tarefa) {
		this.id = tarefa.getCdTarefa();
		this.titulo = tarefa.getTitulo();
		this.executado = tarefa.getExecutado();
		this.data = tarefa.getData();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getExecutado() {
		return executado;
	}

	public void setExecutado(Boolean executado) {
		this.executado = executado;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
