package org.serratec.sistemacompetenciaback.controller;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sistemacompetenciaback.dto.CategoriaDTO;
import org.serratec.sistemacompetenciaback.entity.Categoria;
import org.serratec.sistemacompetenciaback.services.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	private static List<Categoria> listaCategoria = new ArrayList<>();
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaDTO inserir(@RequestBody CategoriaDTO categoriaDTO) {
		categoriaService.save(categoriaDTO);
		return categoriaDTO;
	}
	
	@GetMapping
	public ResponseEntity<?> listar() {
		listaCategoria = categoriaService.findAll();
		return ResponseEntity.ok(listaCategoria);
	}
	 
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable Long id) {
		ResponseEntity<Categoria> categoria = categoriaService.listarPorId(id);
		return categoria;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
		ResponseEntity<Categoria> categorias = categoriaService.atualizar(id, categoria);
		return categorias;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		ResponseEntity<Void> deleteCategoria = categoriaService.deletar(id);
		return deleteCategoria;
	}
	
}
