package com.routineapp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.routineapp.dto.TarefaDTO;
import com.routineapp.entidades.Rotina;
import com.routineapp.entidades.Tarefa;
import com.routineapp.servicos.RotinaServico;
import com.routineapp.servicos.TarefaServico;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaResource {

	@Autowired
	private TarefaServico tarefaService;

	@Autowired
	private RotinaServico rotinaServico;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestParam(value = "id") Long id, @RequestBody Tarefa obj) {
		Rotina rotina = rotinaServico.find(id);
		obj.setRotina(rotina);
		obj = tarefaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCdTarefa())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TarefaDTO objDto, @PathVariable Long id) {
		Tarefa obj = tarefaService.fromDTO(objDto);
		obj.setCdTarefa(id);
		obj = tarefaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		tarefaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TarefaDTO>> findAll(@RequestParam(value = "id") Long id) {
		Rotina rotina = rotinaServico.find(id);
		List<Tarefa> list = tarefaService.findByRotina(rotina);
		List<TarefaDTO> listDto = list.stream().map(obj -> new TarefaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
