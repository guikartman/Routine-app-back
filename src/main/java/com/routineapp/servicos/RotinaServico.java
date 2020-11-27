package com.routineapp.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Usuario;
import com.routineapp.repositorios.RotinaRepositorio;
import com.routineapp.servicos.exceptions.DatabaseException;
import com.routineapp.servicos.exceptions.ResourceNotFoundException;

@Service
public class RotinaServico {
	
	@Autowired
	private RotinaRepositorio repo;
	
	public Rotina find(Long id) {
		Rotina obj = ((Optional<Rotina>) repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public List<Rotina> findByUser(Usuario user) {

		List<Rotina> obj = repo.findByUser(user);

		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado!" + ", Tipo: " + Rotina.class.getName());
		}
		return obj;
	}

	public Rotina insert(Rotina obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}


	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir porque há pedidos relacionados");
		}
	}

}
