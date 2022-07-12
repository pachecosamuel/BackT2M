package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.ColaboradorDTO;
import org.serratec.sistemacompetenciaback.dto.ColaboradorUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Colaborador;
import org.serratec.sistemacompetenciaback.services.ColaboradorService;
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
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	@GetMapping
	public ResponseEntity<List<Colaborador>> findAll() {
		return ResponseEntity.ok(colaboradorService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
		return colaboradorService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Colaborador> save(@Valid @RequestBody ColaboradorDTO colaboradorDTO) {
		return colaboradorService.save(colaboradorDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Colaborador> update(@PathVariable Long id,
			@Valid @RequestBody ColaboradorUpdateDTO colaboradorUpdateDTO) {
		return colaboradorService.update(id, colaboradorUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return colaboradorService.deleteById(id);
	}
}
