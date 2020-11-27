package com.routineapp.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.routineapp.dto.TarefaDTO;
import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Tarefa;
import com.routineapp.entidades.Usuario;
import com.routineapp.repositorios.TarefaRepositorio;
import com.routineapp.servicos.exceptions.DatabaseException;
import com.routineapp.servicos.exceptions.ResourceNotFoundException;

@Service
public class TarefaServico {
	
	@Autowired
	private TarefaRepositorio repo;
	
	public Tarefa find(Long id) {
		Tarefa obj = ((Optional<Tarefa>) repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public List<Tarefa> findByRotina(Rotina rotina) {

		List<Tarefa> obj = repo.findByRotinaOrderByDataDesc(rotina);

		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado!" + ", Tipo: " + Tarefa.class.getName());
		}
		return obj;
	}

	public Tarefa insert(Tarefa obj) {
		obj.setCdTarefa(null);
		obj = repo.save(obj);
		return obj;
	}

	public Tarefa update(Tarefa obj) {
		Tarefa newObj = find(obj.getCdTarefa());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public Tarefa fromDTO(TarefaDTO tarefaDTO) {
		return new Tarefa(tarefaDTO.getId(), tarefaDTO.getTitulo(), tarefaDTO.getExecutado(), tarefaDTO.getData());
	}

	public void updateData(Tarefa newObj, Tarefa obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setExecutado(obj.getExecutado());
		newObj.setData(obj.getData());
	}
}
