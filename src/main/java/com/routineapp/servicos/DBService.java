package com.routineapp.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Usuario;
import com.routineapp.repositorios.RotinaRepositorio;
import com.routineapp.repositorios.UsuarioRepositorio;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Autowired
	private RotinaRepositorio rotinaRepository;

	public void instantiateTestDatabase() {
		
		Usuario usu1 = new Usuario(null, "Guilherme Dantas", "guikartman@gmail.com", pe.encode("meutime123"));
		
		usuarioRepository.save(usu1);

		Rotina rot1 = new Rotina(null, "Rotina Teste", usu1);

		rotinaRepository.save(rot1);
	}
	
	
}
