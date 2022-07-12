package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.ProfissionalDTO;
import org.serratec.sistemacompetenciaback.dto.ProfissionalUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Profissional;
import org.serratec.sistemacompetenciaback.services.ProfissionalService;
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
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	
	

	@GetMapping
	public ResponseEntity<List<Profissional>> findAll() {
		return ResponseEntity.ok(profissionalService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profissional> findById(@PathVariable Long id) {
		return profissionalService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Profissional> save(@Valid @RequestBody ProfissionalDTO profissionalDTO) {
		return profissionalService.save(profissionalDTO);
	}
	
	@PostMapping("/empresa/{empresa}")
	public Profissional saveProf(@Valid @RequestBody Profissional profissional, @PathVariable String empresa) {
		return profissionalService.inserir(profissional, empresa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profissional> update(@PathVariable Long id,
			@Valid @RequestBody ProfissionalUpdateDTO profissionalUpdateDTO) {
		return profissionalService.update(id, profissionalUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return profissionalService.deleteById(id);
	}
	
	@PostMapping("/{idProfissional}/add/{idEmpresa}")
	public ResponseEntity<Void> add(@PathVariable Long idProfissional, @PathVariable Long idEmpresa) {
		return profissionalService.add(idProfissional, idEmpresa);
	}
	
	@PostMapping("/{idProfissional}/remove/{idEmpresa}")
	public ResponseEntity<Void> remove(@PathVariable Long idProfissional, @PathVariable Long idEmpresa) {
		return profissionalService.remove(idProfissional, idEmpresa);
	}
	
	@PostMapping("/{idProfissional}/add/{idTecnologia}")
	public ResponseEntity<Void> addTec(@PathVariable Long idProfissional, @PathVariable Long idTecnologia) {
		return profissionalService.addTec(idProfissional, idTecnologia);
	}
	
	@PostMapping("/{idProfissional}/remove/{idTecnologia}")
	public ResponseEntity<Void> removeTec(@PathVariable Long idProfissional, @PathVariable Long idTecnologia) {
		return profissionalService.removeTec(idProfissional, idTecnologia);
	}
	

}
