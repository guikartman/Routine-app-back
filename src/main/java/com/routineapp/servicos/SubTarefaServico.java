package com.routineapp.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.routineapp.dto.SubTarefaDTO;
import com.routineapp.entidades.SubTarefa;
import com.routineapp.entidades.Tarefa;
import com.routineapp.repositorios.SubTarefaRepositorio;
import com.routineapp.servicos.exceptions.DatabaseException;
import com.routineapp.servicos.exceptions.ResourceNotFoundException;

@Service
public class SubTarefaServico {
	
	@Autowired
	private SubTarefaRepositorio repo;
	
	public SubTarefa find(Long id) {
		SubTarefa obj = ((Optional<SubTarefa>) repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + SubTarefa.class.getName());
		}
		return obj;
	}

	public List<SubTarefa> findByTarefa(Tarefa tarefa) {

		List<SubTarefa> obj = repo.findByTarefa(tarefa);

		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado!" + ", Tipo: " + SubTarefa.class.getName());
		}
		return obj;
	}

	public SubTarefa insert(SubTarefa obj) {
		obj.setCdSubTarefa(null);
		obj = repo.save(obj);
		return obj;
	}

	public SubTarefa update(SubTarefa obj) {
		SubTarefa newObj = find(obj.getCdSubTarefa());
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

	public SubTarefa fromDTO(SubTarefaDTO SubTarefaDTO) {
		return new SubTarefa(SubTarefaDTO.getId(), SubTarefaDTO.getTituloSubTarefa(), SubTarefaDTO.getExecutado());
	}

	public void updateData(SubTarefa newObj, SubTarefa obj) {
		newObj.setTituloSubTarefa(obj.getTituloSubTarefa());
		newObj.setExecutado(obj.getExecutado());
	}
}
