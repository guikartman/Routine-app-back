package com.routineapp.repositorios;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.routineapp.entidades.SubTarefa;
import com.routineapp.entidades.Tarefa;

@Repository
public interface SubTarefaRepositorio extends JpaRepository<SubTarefa, Long> {

	@Transactional(readOnly = true)
	List<SubTarefa> findByTarefa(Tarefa tarefa);

}
