package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.RequisitoDTO;
import org.serratec.sistemacompetenciaback.dto.RequisitoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.serratec.sistemacompetenciaback.entity.Requisito;
import org.serratec.sistemacompetenciaback.services.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requisitos")
public class RequisitoController {

	@Autowired
	private RequisitoService requisitoService;

	@GetMapping
	public ResponseEntity<List<Requisito>> findAll() {
		return ResponseEntity.ok(requisitoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Requisito> findById(@PathVariable Long id) {
		return requisitoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Requisito> save(@Valid @RequestBody RequisitoDTO requisitoDTO) {
		return requisitoService.save(requisitoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Requisito> update(@PathVariable Long id,
			@Valid @RequestBody RequisitoUpdateDTO requisitoUpdateDTO) {
		return requisitoService.update(id, requisitoUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return requisitoService.deleteById(id);
	}

	@PostMapping("/{idRequisito}/add/{idTreinamento}")
	public ResponseEntity<Void> add(@PathVariable Long idRequisito, @PathVariable Long idTreinamento) {
		return requisitoService.add(idRequisito, idTreinamento);
	}
	
	@PostMapping("/{idRequisito}/remove/{idTreinamento}")
	public ResponseEntity<Void> remove(@PathVariable Long idRequisito, @PathVariable Long idTreinamento) {
		return requisitoService.remove(idRequisito, idTreinamento);
	}
	
	@GetMapping("/{id}/conhecimentos")
	public ResponseEntity<List<Conhecimento>> findRelations(@PathVariable Long id) {
		return requisitoService.findRelations(id);
	}

}
