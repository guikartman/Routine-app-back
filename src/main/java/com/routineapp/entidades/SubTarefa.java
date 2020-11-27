package com.routineapp.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_subtarefa")
public class SubTarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdSubTarefa;

	@Column(name = "titulo_sub_tarefa", nullable = false)
	private String tituloSubTarefa;

	@Column(name = "in_executado", columnDefinition = "boolean default false")
	private Boolean executado;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "cd_tarefa")
	private Tarefa tarefa;

	public SubTarefa() {

	}

	public SubTarefa(Long cdSubTarefa, String tituloSubTarefa, Boolean executado) {
		super();
		this.cdSubTarefa = cdSubTarefa;
		this.tituloSubTarefa = tituloSubTarefa;
		this.executado = executado;
	}

	public Long getCdSubTarefa() {
		return cdSubTarefa;
	}

	public void setCdSubTarefa(Long cdSubTarefa) {
		this.cdSubTarefa = cdSubTarefa;
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

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdSubTarefa == null) ? 0 : cdSubTarefa.hashCode());
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
		SubTarefa other = (SubTarefa) obj;
		if (cdSubTarefa == null) {
			if (other.cdSubTarefa != null)
				return false;
		} else if (!cdSubTarefa.equals(other.cdSubTarefa))
			return false;
		return true;
	}

}
