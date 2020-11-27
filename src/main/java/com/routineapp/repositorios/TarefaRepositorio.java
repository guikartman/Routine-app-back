package com.routineapp.repositorios;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Tarefa;

@Repository
public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {

	@Transactional(readOnly = true)
	List<Tarefa> findByRotinaOrderByDataDesc(Rotina rotina);

}
