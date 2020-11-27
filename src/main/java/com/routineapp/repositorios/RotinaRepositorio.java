package com.routineapp.repositorios;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Usuario;

@Repository
public interface RotinaRepositorio extends JpaRepository<Rotina, Long> {

	@Transactional(readOnly = true)
	List<Rotina> findByUser(Usuario user);

}
