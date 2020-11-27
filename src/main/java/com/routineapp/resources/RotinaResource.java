package com.routineapp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.routineapp.dto.RotinaDTO;
import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Usuario;
import com.routineapp.servicos.RotinaServico;
import com.routineapp.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/rotinas")
public class RotinaResource {

	@Autowired
	private RotinaServico rotinaService;

	@Autowired
	private UsuarioServico usuarioServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Rotina> find(@PathVariable Long id) {
		Rotina obj = rotinaService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestParam(value = "id") Long id, @RequestBody Rotina obj) {
		Usuario user = usuarioServico.find(id);
		obj.setUser(user);
		obj = rotinaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		rotinaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RotinaDTO>> findAll(@RequestParam(value = "id") Long id) {
		Usuario user = usuarioServico.find(id);
		List<Rotina> list = rotinaService.findByUser(user);
		List<RotinaDTO> listDto = list.stream().map(obj -> new RotinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
