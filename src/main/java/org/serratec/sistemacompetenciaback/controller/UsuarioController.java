package org.serratec.sistemacompetenciaback.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.sistemacompetenciaback.dto.UsuarioDTO;
import org.serratec.sistemacompetenciaback.dto.UsuarioUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.serratec.sistemacompetenciaback.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@GetMapping("/login/{login}")
	public ResponseEntity<Usuario> findByLogin(@PathVariable String login) {
		return usuarioService.findByLogin(login);
	}

	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.save(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id,
			@Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
		return usuarioService.update(id, usuarioUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		return usuarioService.deleteById(id);
	}
}
