package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.TecnologiaDTO;
import org.serratec.sistemacompetenciaback.entity.Tecnologia;
import org.serratec.sistemacompetenciaback.repositories.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnologiaService {
	
	@Autowired
	private TecnologiaRepository tecnologiasRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Tecnologia> findAll(){
		return tecnologiasRepository.findAll();
	}
	
	public ResponseEntity<Tecnologia> listarPorId(Long id) {
		Optional<Tecnologia> opTecnologias = tecnologiasRepository.findById(id);
		if(opTecnologias.isPresent()) {
			return ResponseEntity.ok(opTecnologias.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	public Tecnologia inserir(Tecnologia tecnologias) {
		Tecnologia tecnologia = tecnologiasRepository.save(tecnologias);
		return tecnologia;
	}
	
	@Transactional
	public ResponseEntity<Tecnologia> atualizar(Long id, Tecnologia tecnologias) {
		Optional<Tecnologia> tecnologia = tecnologiasRepository.findById(id);
		
		if(tecnologia.isPresent()) {
			if(null != tecnologias.getNome()) {
				tecnologia.get().setNome(tecnologias.getNome());
			}
			if(null != tecnologias.getCategoria()) {
				tecnologia.get().setCategoria(tecnologias.getCategoria());
			}	
			else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(tecnologias = tecnologiasRepository.save(tecnologia.get()));
	}
	
	@Transactional
	public ResponseEntity<Void> deletar(Long id) {
		if(!tecnologiasRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		tecnologiasRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public ResponseEntity<Tecnologia> save(TecnologiaDTO tecnologiaDTO) {
		Tecnologia tecnologia = modelMapper.map(tecnologiaDTO, Tecnologia.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(tecnologiasRepository.save(tecnologia));
	}
	
}