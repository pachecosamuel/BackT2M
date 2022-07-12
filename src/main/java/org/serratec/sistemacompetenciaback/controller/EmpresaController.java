package org.serratec.sistemacompetenciaback.controller;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sistemacompetenciaback.entity.Empresa;
import org.serratec.sistemacompetenciaback.services.EmpresaService;
import org.serratec.sistemacompetenciaback.services.ProfissionalService;
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
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa inserir(@RequestBody Empresa empresas) {
		empresaService.save(empresas);
		return empresas;
	}
	
	@GetMapping
	public ResponseEntity<?> lista(){
		listaEmpresas = empresaService.findAll();
		return ResponseEntity.ok(listaEmpresas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscaId(@PathVariable Long id){
	      ResponseEntity<Empresa> empresas = empresaService.buscaId(id);
	      return empresas;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresas){
		ResponseEntity<Empresa> empresa = empresaService.atualizar(id, empresas);
		return empresa;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
		ResponseEntity<Void> deleteEmpresas = empresaService.deletarPorId(id);
		return deleteEmpresas;
	}
	
	@PostMapping("/{idEmpresa}/add/{idProfissional}")
	public ResponseEntity<Void> add(@PathVariable Long idEmpresa, @PathVariable Long idProfissional) {
		return profissionalService.add(idProfissional, idEmpresa);
	}
	
	@PostMapping("/{idEmpresa}/remove/{idProfissional}")
	public ResponseEntity<Void> remove(@PathVariable Long idEmpresa, @PathVariable Long idProfissional) {
		return profissionalService.remove(idProfissional, idEmpresa);
	}

}
