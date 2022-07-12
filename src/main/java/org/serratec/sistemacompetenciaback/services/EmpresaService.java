package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.serratec.sistemacompetenciaback.entity.Empresa;
import org.serratec.sistemacompetenciaback.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<Empresa> findAll() {
		return empresaRepository.findAll();
	}

	public Empresa inserir(Empresa empresas) {
		Empresa empresa = empresaRepository.save(empresas);
		return empresa;
	}

	public ResponseEntity<Empresa> buscaId(Long id) {
		Optional<Empresa> empresas = empresaRepository.findById(id);
		if (empresas.isPresent()) {
			return ResponseEntity.ok(empresas.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Empresa> atualizar(Long id, Empresa empresas) {
		Optional<Empresa> empresa = empresaRepository.findById(id);

		if (empresa.isPresent()) {
			if (null != empresas.getNome()) {
				empresa.get().setNome(empresas.getNome());
			}
			if (null != empresas.getCnpj()) {
				empresa.get().setCnpj(empresas.getCnpj());
			}
			if (null != empresas.getRazaoSocial()) {
				empresa.get().setRazaoSocial(empresas.getRazaoSocial());
			}
			if (null != empresas.getAreaAtuacao()) {
				empresa.get().setAreaAtuacao(empresas.getAreaAtuacao());
			}
		} else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresas = empresaRepository.save(empresa.get()));
	}

	public ResponseEntity<Void> deletarPorId(Long id) {
		if (!empresaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		empresaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	public void save(Empresa empresas) {
		empresaRepository.save(empresas);
	}

}
