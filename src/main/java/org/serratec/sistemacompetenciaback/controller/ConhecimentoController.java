package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.ConhecimentoDTO;
import org.serratec.sistemacompetenciaback.dto.ConhecimentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.serratec.sistemacompetenciaback.entity.PossivelTreinamento;
import org.serratec.sistemacompetenciaback.services.ConhecimentoService;
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
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

	@Autowired
	private ConhecimentoService conhecimentoService;

	@GetMapping
	public ResponseEntity<List<Conhecimento>> findAll() {
		return ResponseEntity.ok(conhecimentoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conhecimento> findById(@PathVariable Long id) {
		return conhecimentoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Conhecimento> save(@Valid @RequestBody ConhecimentoDTO conhecimentoDTO) {
		return conhecimentoService.save(conhecimentoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Conhecimento> update(@PathVariable Long id,
			@Valid @RequestBody ConhecimentoUpdateDTO conhecimentoUpdateDTO) {
		return conhecimentoService.update(id, conhecimentoUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return conhecimentoService.deleteById(id);
	}
	
	@PostMapping("/{idConhecimento}/add/{idRequisito}")
	public ResponseEntity<Void> add(@PathVariable Long idConhecimento, @PathVariable Long idRequisito) {
		return conhecimentoService.add(idConhecimento, idRequisito);
	}
	
	@PostMapping("/{idConhecimento}/remove/{idRequisito}")
	public ResponseEntity<Void> remove(@PathVariable Long idConhecimento, @PathVariable Long idRequisito) {
		return conhecimentoService.remove(idConhecimento, idRequisito);
	}
	
	@GetMapping("/{id}/possiveistreinamentos")
	public ResponseEntity<List<PossivelTreinamento>> findRelations(@PathVariable Long id) {
		return conhecimentoService.findRelations(id);
	}
	
}
