package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.TreinamentoDTO;
import org.serratec.sistemacompetenciaback.dto.TreinamentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Requisito;
import org.serratec.sistemacompetenciaback.entity.Treinamento;
import org.serratec.sistemacompetenciaback.repositories.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TreinamentoService {

	@Autowired
	private TreinamentoRepository treinamentoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Treinamento> findAll() {
		return treinamentoRepository.findAll();
	}

	public ResponseEntity<Treinamento> findById(Long id) {
		Optional<Treinamento> opTreinamento = treinamentoRepository.findById(id);

		if (opTreinamento.isPresent()) {
			return ResponseEntity.ok(opTreinamento.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Treinamento> save(TreinamentoDTO treinamentoDTO) {
		Treinamento treinamento = modelMapper.map(treinamentoDTO, Treinamento.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(treinamentoRepository.save(treinamento));
	}

	@Transactional
	public ResponseEntity<Treinamento> update(Long id, TreinamentoUpdateDTO treinamentoUpdateDTO) {
		Optional<Treinamento> opTreinamento = treinamentoRepository.findById(id);

		if (opTreinamento.isPresent()) {
			if (null != treinamentoUpdateDTO.getPosicao()) {
				opTreinamento.get().setPosicao(treinamentoUpdateDTO.getPosicao());
			}
			if (null != treinamentoUpdateDTO.getDescricao()) {
				opTreinamento.get().setDescricao(treinamentoUpdateDTO.getDescricao());
			}
			return ResponseEntity.ok(treinamentoRepository.save(opTreinamento.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deletarPorId(Long id) {
		if (!treinamentoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		treinamentoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<List<Requisito>> findRelations(Long id) {
		Optional<Treinamento> treinamentoOptional= treinamentoRepository.findById(id);

		if (treinamentoOptional.isPresent()) {
			
			return ResponseEntity.ok(treinamentoOptional.get().getRequisitos());
		}
		return ResponseEntity.notFound().build();
	}

}
