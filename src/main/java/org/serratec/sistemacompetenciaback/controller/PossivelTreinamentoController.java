package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.PossivelTreinamentoDTO;
import org.serratec.sistemacompetenciaback.dto.PossivelTreinamentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.PossivelTreinamento;
import org.serratec.sistemacompetenciaback.services.PossivelTreinamentoService;
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
@RequestMapping("/possiveistreinamentos")
public class PossivelTreinamentoController {

	@Autowired
	private PossivelTreinamentoService possivelTreinamentoService;

	@GetMapping
	public ResponseEntity<List<PossivelTreinamento>> findAll() {
		return ResponseEntity.ok(possivelTreinamentoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PossivelTreinamento> findById(@PathVariable Long id) {
		return possivelTreinamentoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<PossivelTreinamento> save(@Valid @RequestBody PossivelTreinamentoDTO possivelTreinamentoDTO) {
		return possivelTreinamentoService.save(possivelTreinamentoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PossivelTreinamento> update(@PathVariable Long id,
			@Valid @RequestBody PossivelTreinamentoUpdateDTO possivelTreinamentoUpdateDTO) {
		return possivelTreinamentoService.update(id, possivelTreinamentoUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return possivelTreinamentoService.deleteById(id);
	}

	@PostMapping("/{idPossivelTreinamento}/add/{idConhecimento}")
	public ResponseEntity<Void> add(@PathVariable Long idPossivelTreinamento, @PathVariable Long idConhecimento) {
		return possivelTreinamentoService.add(idPossivelTreinamento, idConhecimento);
	}
	
	@PostMapping("/{idPossivelTreinamento}/remove/{idConhecimento}")
	public ResponseEntity<Void> remove(@PathVariable Long idPossivelTreinamento, @PathVariable Long idConhecimento) {
		return possivelTreinamentoService.remove(idPossivelTreinamento, idConhecimento);
	}

}
