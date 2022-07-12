package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.TreinamentoDTO;
import org.serratec.sistemacompetenciaback.dto.TreinamentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Requisito;
import org.serratec.sistemacompetenciaback.entity.Treinamento;
import org.serratec.sistemacompetenciaback.services.TreinamentoService;
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
@RequestMapping("/treinamentos")
public class TreinamentoController {

	@Autowired
	private TreinamentoService treinamentoService;

	@GetMapping
	public ResponseEntity<List<Treinamento>> findAll() {
		return ResponseEntity.ok(treinamentoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Treinamento> findById(@PathVariable Long id) {
		return treinamentoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Treinamento> save(@Valid @RequestBody TreinamentoDTO treinamentoDTO) {
		return treinamentoService.save(treinamentoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Treinamento> update(@PathVariable Long id,
			@RequestBody TreinamentoUpdateDTO treinamentoUpdateDTO) {
		return treinamentoService.update(id, treinamentoUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		return treinamentoService.deletarPorId(id);
	}
	
	@GetMapping("/{id}/requisitos")
	public ResponseEntity<List<Requisito>> findRelations(@PathVariable Long id) {
		return treinamentoService.findRelations(id);
	}

}
