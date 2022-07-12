package org.serratec.sistemacompetenciaback.controller;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sistemacompetenciaback.dto.TecnologiaDTO;
import org.serratec.sistemacompetenciaback.entity.Tecnologia;
import org.serratec.sistemacompetenciaback.services.ProfissionalService;
import org.serratec.sistemacompetenciaback.services.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnologias")
public class TecnologiaController {
	
	@Autowired
	private TecnologiaService tecnologiasService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	private static List<Tecnologia> listaTecnologias = new ArrayList<>();
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TecnologiaDTO inserir(@RequestBody TecnologiaDTO tecnologiasDTO){
		tecnologiasService.save(tecnologiasDTO);
		return tecnologiasDTO;
	}
	
	@GetMapping
	public ResponseEntity<?> lista(){
		listaTecnologias = tecnologiasService.findAll();
		return ResponseEntity.ok(listaTecnologias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tecnologia> listarPorId(@PathVariable Long id) {
		ResponseEntity<Tecnologia> tecnologias = tecnologiasService.listarPorId(id);
		return tecnologias;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tecnologia> atualizar(@PathVariable Long id, @RequestBody Tecnologia tecnologias){
		ResponseEntity<Tecnologia> tecnologia = tecnologiasService.atualizar(id, tecnologias);
		return tecnologia;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		ResponseEntity<Void> deleteTecnologias = tecnologiasService.deletar(id);
		return deleteTecnologias;
	}
	
	@PostMapping("/{idTecnologia}/add/{idProfissional}")
	public ResponseEntity<Void> addTec(@PathVariable Long idTecnologia, @PathVariable Long idProfissional) {
		return profissionalService.addTec(idProfissional, idTecnologia);
	}
	
	@PostMapping("/{idTecnologia}/remove/{idProfissional}")
	public ResponseEntity<Void> removeTec(@PathVariable Long idTecnologia, @PathVariable Long idProfissional) {
		return profissionalService.removeTec(idProfissional, idTecnologia);
	}

}
