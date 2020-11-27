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

import com.routineapp.dto.SubTarefaDTO;
import com.routineapp.entidades.SubTarefa;
import com.routineapp.entidades.Tarefa;
import com.routineapp.servicos.SubTarefaServico;
import com.routineapp.servicos.TarefaServico;

@RestController
@RequestMapping(value = "/subtarefas")
public class SubTarefaResource {

	@Autowired
	private TarefaServico tarefaService;

	@Autowired
	private SubTarefaServico subtarefaService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody SubTarefa obj, @RequestParam(value = "id") Long id) {
		Tarefa tarefa = tarefaService.find(id);
		obj.setTarefa(tarefa);
		obj = subtarefaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCdSubTarefa())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SubTarefaDTO objDto, @PathVariable Long id) {
		SubTarefa obj = subtarefaService.fromDTO(objDto);
		obj.setCdSubTarefa(id);
		obj = subtarefaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		subtarefaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SubTarefaDTO>> findAll(@RequestParam(value = "id") Long id) {
		Tarefa tarefa = tarefaService.find(id);
		List<SubTarefa> list = subtarefaService.findByTarefa(tarefa);
		List<SubTarefaDTO> listDto = list.stream().map(obj -> new SubTarefaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
