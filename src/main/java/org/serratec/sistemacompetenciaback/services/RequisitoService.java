package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.RequisitoDTO;
import org.serratec.sistemacompetenciaback.dto.RequisitoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.serratec.sistemacompetenciaback.entity.Requisito;
import org.serratec.sistemacompetenciaback.entity.Treinamento;
import org.serratec.sistemacompetenciaback.repositories.RequisitoRepository;
import org.serratec.sistemacompetenciaback.repositories.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequisitoService {

	@Autowired
	private RequisitoRepository requisitoRepository;

	@Autowired
	private TreinamentoRepository treinamentoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Requisito> findAll() {
		return requisitoRepository.findAll();
	}

	public ResponseEntity<Requisito> findById(Long id) {
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(id);

		if (requisitoOptional.isPresent()) {
			return ResponseEntity.ok(requisitoOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Requisito> save(RequisitoDTO requisitoDTO) {
		Requisito requisito = modelMapper.map(requisitoDTO, Requisito.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(requisitoRepository.save(requisito));
	}

	@Transactional
	public ResponseEntity<Requisito> update(Long id, RequisitoUpdateDTO requisitoUpdateDTO) {
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(id);

		if (requisitoOptional.isPresent()) {
			if (null != requisitoUpdateDTO.getNome())
				requisitoOptional.get().setNome(requisitoUpdateDTO.getNome());

			if (null != requisitoUpdateDTO.getDescricao())
				requisitoOptional.get().setDescricao(requisitoUpdateDTO.getDescricao());

			return ResponseEntity.ok(requisitoRepository.save(requisitoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!requisitoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		requisitoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Transactional
	public ResponseEntity<Void> add(Long idRequisito, Long idTreinamento) {
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(idRequisito);
		Optional<Treinamento> treinamentoOptional = treinamentoRepository.findById(idTreinamento);

		if (requisitoOptional.isPresent() && treinamentoOptional.isPresent()) {
			
			List<Treinamento> treinamentos = requisitoOptional.get().getTreinamentos();
			List<Requisito> requisitos = treinamentoOptional.get().getRequisitos();
			
			Optional<Requisito> requisitoOp = requisitos.stream().filter(requisito -> requisito.getId().equals(idRequisito))
					.findFirst();
			
			Optional<Treinamento> treinamentoOp = treinamentos.stream().filter(treinamento -> treinamento.getId().equals(idTreinamento))
					.findFirst();
			
			if (!requisitoOp.isPresent() && !treinamentoOp.isPresent()) {
				treinamentos.add(treinamentoOptional.get());
				requisitos.add(requisitoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> remove(Long idRequisito, Long idTreinamento) {
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(idRequisito);
		Optional<Treinamento> treinamentoOptional = treinamentoRepository.findById(idTreinamento);

		if (requisitoOptional.isPresent() && treinamentoOptional.isPresent()) {
			
			List<Treinamento> treinamentos = requisitoOptional.get().getTreinamentos();
			List<Requisito> requisitos = treinamentoOptional.get().getRequisitos();
			
			Optional<Requisito> requisitoOp = requisitos.stream().filter(requisito -> requisito.getId().equals(idRequisito))
					.findFirst();
			
			Optional<Treinamento> treinamentoOp = treinamentos.stream().filter(treinamento -> treinamento.getId().equals(idTreinamento))
					.findFirst();
				
			if (requisitoOp.isPresent() && treinamentoOp.isPresent()) {
				treinamentos.remove(treinamentoOptional.get());
				requisitos.remove(requisitoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<Conhecimento>> findRelations(Long id) {
		Optional<Requisito> requisitoOptional= requisitoRepository.findById(id);

		if (requisitoOptional.isPresent()) {
			
			return ResponseEntity.ok(requisitoOptional.get().getConhecimentos());
		}
		return ResponseEntity.notFound().build();
	}

}
