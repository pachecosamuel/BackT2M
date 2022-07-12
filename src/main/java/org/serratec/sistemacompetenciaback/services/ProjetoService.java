package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.ProjetoDTO;
import org.serratec.sistemacompetenciaback.entity.Colaborador;
import org.serratec.sistemacompetenciaback.entity.Projeto;
import org.serratec.sistemacompetenciaback.repositories.ColaboradorRepository;
import org.serratec.sistemacompetenciaback.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Projeto> findAll() {
		return projetoRepository.findAll();
	}

	public ResponseEntity<Projeto> findById(Long id) {
		Optional<Projeto> projetoOptional = projetoRepository.findById(id);

		if (projetoOptional.isPresent()) {
			return ResponseEntity.ok(projetoOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Projeto> save(ProjetoDTO projetoDTO) {
		Projeto projeto = modelMapper.map(projetoDTO, Projeto.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(projetoRepository.save(projeto));
	}
	
	@Transactional
	public ResponseEntity<Void> connect(Long idProjeto, Long idColaborador) {
		Optional<Projeto> projetoOptional = projetoRepository.findById(idProjeto);
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(idColaborador);
		
		if (projetoOptional.isPresent() && colaboradorOptional.isPresent()) {
//			projetoOptional.get().getColaboradores().add(colaboradorOptional.get());


			colaboradorOptional.get().getProjetos().add(projetoOptional.get());
			
		return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!projetoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		projetoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
