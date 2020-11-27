package com.routineapp.dto;

import java.io.Serializable;

import com.routineapp.entidades.Rotina;

public class RotinaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public RotinaDTO() {

	}

	public RotinaDTO(Rotina obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
