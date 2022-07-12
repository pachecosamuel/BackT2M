package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.PossivelTreinamentoDTO;
import org.serratec.sistemacompetenciaback.dto.PossivelTreinamentoUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.serratec.sistemacompetenciaback.entity.PossivelTreinamento;
import org.serratec.sistemacompetenciaback.repositories.ConhecimentoRepository;
import org.serratec.sistemacompetenciaback.repositories.PossivelTreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PossivelTreinamentoService {

	@Autowired
	private PossivelTreinamentoRepository possivelTreinamentoRepository;

	@Autowired
	private ConhecimentoRepository conhecimentoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<PossivelTreinamento> findAll() {
		return possivelTreinamentoRepository.findAll();
	}

	public ResponseEntity<PossivelTreinamento> findById(Long id) {
		Optional<PossivelTreinamento> possivelTreinamentoOptional = possivelTreinamentoRepository.findById(id);

		if (possivelTreinamentoOptional.isPresent()) {
			return ResponseEntity.ok(possivelTreinamentoOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<PossivelTreinamento> save(PossivelTreinamentoDTO possivelTreinamentoDTO) {
		PossivelTreinamento possivelTreinamento = modelMapper.map(possivelTreinamentoDTO, PossivelTreinamento.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(possivelTreinamentoRepository.save(possivelTreinamento));
	}

	@Transactional
	public ResponseEntity<PossivelTreinamento> update(Long id,
			PossivelTreinamentoUpdateDTO possivelTreinamentoUpdateDTO) {
		Optional<PossivelTreinamento> possivelTreinamentoOptional = possivelTreinamentoRepository.findById(id);

		if (possivelTreinamentoOptional.isPresent()) {
			if (null != possivelTreinamentoUpdateDTO.getNome())
				possivelTreinamentoOptional.get().setNome(possivelTreinamentoUpdateDTO.getNome());

			if (null != possivelTreinamentoUpdateDTO.getDescricao())
				possivelTreinamentoOptional.get().setDescricao(possivelTreinamentoUpdateDTO.getDescricao());

			return ResponseEntity.ok(possivelTreinamentoRepository.save(possivelTreinamentoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!possivelTreinamentoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		possivelTreinamentoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Transactional
	public ResponseEntity<Void> add(Long idPossivelTreinamento, Long idConhecimento) {
		Optional<PossivelTreinamento> possivelTreinamentoOptional = possivelTreinamentoRepository
				.findById(idPossivelTreinamento);
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(idConhecimento);

		if (possivelTreinamentoOptional.isPresent() && conhecimentoOptional.isPresent()) {

			List<Conhecimento> conhecimentos = possivelTreinamentoOptional.get().getConhecimentos();
			List<PossivelTreinamento> possiveisTreinamentos = conhecimentoOptional.get().getPossiveisTreinamentos();

			Optional<PossivelTreinamento> possivelTreinamentoOp = possiveisTreinamentos.stream()
					.filter(possivelTreinamento -> possivelTreinamento.getId().equals(idPossivelTreinamento))
					.findFirst();

			Optional<Conhecimento> conhecimentoOp = conhecimentos.stream()
					.filter(conhecimento -> conhecimento.getId().equals(idConhecimento)).findFirst();

			if (!possivelTreinamentoOp.isPresent() && !conhecimentoOp.isPresent()) {
				possiveisTreinamentos.add(possivelTreinamentoOptional.get());
				conhecimentos.add(conhecimentoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> remove(Long idPossivelTreinamento, Long idConhecimento) {
		Optional<PossivelTreinamento> possivelTreinamentoOptional = possivelTreinamentoRepository
				.findById(idPossivelTreinamento);
		Optional<Conhecimento> conhecimentoOptional = conhecimentoRepository.findById(idConhecimento);

		if (possivelTreinamentoOptional.isPresent() && conhecimentoOptional.isPresent()) {

			List<Conhecimento> conhecimentos = possivelTreinamentoOptional.get().getConhecimentos();
			List<PossivelTreinamento> possiveisTreinamentos = conhecimentoOptional.get().getPossiveisTreinamentos();

			Optional<PossivelTreinamento> possivelTreinamentoOp = possiveisTreinamentos.stream()
					.filter(possivelTreinamento -> possivelTreinamento.getId().equals(idPossivelTreinamento))
					.findFirst();

			Optional<Conhecimento> conhecimentoOp = conhecimentos.stream()
					.filter(conhecimento -> conhecimento.getId().equals(idConhecimento)).findFirst();

			if (possivelTreinamentoOp.isPresent() && conhecimentoOp.isPresent()) {
				possiveisTreinamentos.remove(possivelTreinamentoOptional.get());
				conhecimentos.remove(conhecimentoOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

}
