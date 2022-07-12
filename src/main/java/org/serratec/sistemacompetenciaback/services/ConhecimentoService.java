package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.ConhecimentoDTO;
import org.serratec.sistemacompetenciaback.dto.ConhecimentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.serratec.sistemacompetenciaback.entity.PossivelTreinamento;
import org.serratec.sistemacompetenciaback.entity.Requisito;
import org.serratec.sistemacompetenciaback.repositories.ConhecimentoRepository;
import org.serratec.sistemacompetenciaback.repositories.RequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConhecimentoService {

	@Autowired
	private ConhecimentoRepository conhecimentoRepository;

	@Autowired
	private RequisitoRepository requisitoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Conhecimento> findAll() {
		return conhecimentoRepository.findAll();
	}

	public ResponseEntity<Conhecimento> findById(Long id) {
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(id);

		if (conhecimentoOptional.isPresent()) {
			return ResponseEntity.ok(conhecimentoOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Conhecimento> save(ConhecimentoDTO conhecimentoDTO) {
		Conhecimento conhecimento = modelMapper.map(conhecimentoDTO, Conhecimento.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(conhecimentoRepository.save(conhecimento));
	}

	@Transactional
	public ResponseEntity<Conhecimento> update(Long id, ConhecimentoUpdateDTO conhecimentoUpdateDTO) {
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(id);

		if (conhecimentoOptional.isPresent()) {
			if (null != conhecimentoUpdateDTO.getNome())
				conhecimentoOptional.get().setNome(conhecimentoUpdateDTO.getNome());

			if (null != conhecimentoUpdateDTO.getDescricao())
				conhecimentoOptional.get().setDescricao(conhecimentoUpdateDTO.getDescricao());

			return ResponseEntity.ok(conhecimentoRepository.save(conhecimentoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!conhecimentoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		conhecimentoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Transactional
	public ResponseEntity<Void> add(Long idConhecimento, Long idRequisito) {
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(idConhecimento);
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(idRequisito);

		if (conhecimentoOptional.isPresent() && requisitoOptional.isPresent()) {
			
			List<Requisito> requisitos = conhecimentoOptional.get().getRequisitos();
			List<Conhecimento> conhecimentos = requisitoOptional.get().getConhecimentos();
			
			Optional<Conhecimento> conhecimentoOp = conhecimentos.stream().filter(conhecimento -> conhecimento.getId().equals(idConhecimento))
					.findFirst();
			
			Optional<Requisito> requisitoOp = requisitos.stream().filter(requisito -> requisito.getId().equals(idRequisito))
					.findFirst();
			
			if (!conhecimentoOp.isPresent() && !requisitoOp.isPresent()) {
				conhecimentos.add(conhecimentoOptional.get());
				requisitos.add(requisitoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> remove(Long idConhecimento, Long idRequisito) {
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(idConhecimento);
		Optional<Requisito> requisitoOptional = requisitoRepository.findById(idRequisito);

		if (conhecimentoOptional.isPresent() && requisitoOptional.isPresent()) {
			
			List<Requisito> requisitos = conhecimentoOptional.get().getRequisitos();
			List<Conhecimento> conhecimentos = requisitoOptional.get().getConhecimentos();
			
			Optional<Conhecimento> conhecimentoOp = conhecimentos.stream().filter(conhecimento -> conhecimento.getId().equals(idConhecimento))
					.findFirst();
			
			Optional<Requisito> requisitoOp = requisitos.stream().filter(requisito -> requisito.getId().equals(idRequisito))
					.findFirst();
			
			if (conhecimentoOp.isPresent() && requisitoOp.isPresent()) {
				conhecimentos.remove(conhecimentoOptional.get());
				requisitos.remove(requisitoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<PossivelTreinamento>> findRelations(Long id) {
		Optional<Conhecimento> conhecimentoOptional= conhecimentoRepository.findById(id);

		if (conhecimentoOptional.isPresent()) {
			
			return ResponseEntity.ok(conhecimentoOptional.get().getPossiveisTreinamentos());
		}
		return ResponseEntity.notFound().build();
	}

}
