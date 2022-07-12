package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.ColaboradorDTO;
import org.serratec.sistemacompetenciaback.dto.ColaboradorUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Colaborador;
import org.serratec.sistemacompetenciaback.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Colaborador> findAll() {
		return colaboradorRepository.findAll();
	}

	public ResponseEntity<Colaborador> findById(Long id) {
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

		if (colaboradorOptional.isPresent()) {
			return ResponseEntity.ok(colaboradorOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Colaborador> save(ColaboradorDTO colaboradorDTO) {
		
		boolean existsUsuario = colaboradorRepository.existsByUsuario(colaboradorDTO.getUsuario());
		boolean existsEmail = colaboradorRepository.existsByEmail(colaboradorDTO.getEmail());

		if (existsUsuario || existsEmail) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		Colaborador colaborador = modelMapper.map(colaboradorDTO, Colaborador.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorRepository.save(colaborador));
	}

	@Transactional
	public ResponseEntity<Colaborador> update(Long id, ColaboradorUpdateDTO colaboradorUpdateDTO) {
		Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

		if (colaboradorOptional.isPresent()) {
			Colaborador colaborador = colaboradorOptional.get();
			
			if (null != colaboradorUpdateDTO.getNome())
				colaborador.setNome(colaboradorUpdateDTO.getNome());
			
			if (null != colaboradorUpdateDTO.getNome())
				colaborador.setNome(colaboradorUpdateDTO.getNome());
			
			if (null != colaboradorUpdateDTO.getUsuario()) {
				if (!colaboradorRepository.existsByUsuario(colaboradorUpdateDTO.getUsuario())) {
					colaborador.setUsuario(colaboradorUpdateDTO.getUsuario());
				} else if (!colaborador.getUsuario().equals(colaboradorUpdateDTO.getUsuario())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}
			
			if (null != colaboradorUpdateDTO.getEmail()) {
				if (!colaboradorRepository.existsByEmail(colaboradorUpdateDTO.getEmail())) {
					colaborador.setEmail(colaboradorUpdateDTO.getEmail());
				} else if (!colaborador.getEmail().equals(colaboradorUpdateDTO.getEmail())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}
			
			if (null != colaboradorUpdateDTO.getPermissao())
				colaborador.setPermissao(colaboradorUpdateDTO.getPermissao());
		
			if (null != colaboradorUpdateDTO.getPosicao())
				colaborador.setPosicao(colaboradorUpdateDTO.getPosicao());
			
			if (null != colaboradorUpdateDTO.getProjetos()) {
				colaborador.setProjetos(colaboradorUpdateDTO.getProjetos());
			}
			
			if (null != colaboradorUpdateDTO.getFuncao())
				colaborador.setFuncao(colaboradorUpdateDTO.getFuncao());	

			return ResponseEntity.ok(colaboradorRepository.save(colaborador));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!colaboradorRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		colaboradorRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
}
