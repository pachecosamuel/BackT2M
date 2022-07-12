package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.ProjetoDTO;
import org.serratec.sistemacompetenciaback.entity.Projeto;
import org.serratec.sistemacompetenciaback.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/projetos")
@RestController
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@GetMapping
	public ResponseEntity<List<Projeto>> findAll() {
		return ResponseEntity.ok(projetoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Projeto> findById(@PathVariable Long id) {
		return projetoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Projeto> save(@Valid @RequestBody ProjetoDTO projetoDTO) {
		return projetoService.save(projetoDTO);
	}
	
	@PostMapping("/{idProjeto}/connect/{idColaborador}")
	public ResponseEntity<Void> connect(@PathVariable Long idProjeto, @PathVariable Long idColaborador) {
		return projetoService.connect(idProjeto, idColaborador);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return projetoService.deleteById(id);
	}

}
