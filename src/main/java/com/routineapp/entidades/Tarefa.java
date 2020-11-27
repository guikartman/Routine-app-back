package com.routineapp.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdTarefa;

	private String titulo;

	@Column(name = "in_executado", columnDefinition = "boolean default false")
	private Boolean executado;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'", timezone = "GMT")
	private LocalDate data;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "cd_rotina")
	private Rotina rotina;

	@OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL)
	private List<SubTarefa> subtarefa = new ArrayList<SubTarefa>();

	public Tarefa() {

	}

	public Tarefa(Long cdTarefa, String titulo, Boolean executado, LocalDate data) {
		this.cdTarefa = cdTarefa;
		this.titulo = titulo;
		this.executado = executado;
		this.data = data;
	}

	public Long getCdTarefa() {
		return cdTarefa;
	}

	public void setCdTarefa(Long cdTarefa) {
		this.cdTarefa = cdTarefa;
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

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}

	public List<SubTarefa> getSubtarefa() {
		return subtarefa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdTarefa == null) ? 0 : cdTarefa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (cdTarefa == null) {
			if (other.cdTarefa != null)
				return false;
		} else if (!cdTarefa.equals(other.cdTarefa))
			return false;
		return true;
	}

}
