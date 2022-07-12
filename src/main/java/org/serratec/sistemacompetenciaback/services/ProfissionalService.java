package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.ProfissionalDTO;
import org.serratec.sistemacompetenciaback.dto.ProfissionalUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Empresa;
import org.serratec.sistemacompetenciaback.entity.Profissional;
import org.serratec.sistemacompetenciaback.entity.Tecnologia;
import org.serratec.sistemacompetenciaback.repositories.EmpresaRepository;
import org.serratec.sistemacompetenciaback.repositories.ProfissionalRepository;
import org.serratec.sistemacompetenciaback.repositories.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TecnologiaRepository tecnologiasRepository;

	public List<Profissional> findAll() {
		return profissionalRepository.findAll();
	}

	public ResponseEntity<Profissional> findById(Long id) {
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

		if (profissionalOptional.isPresent()) {
			return ResponseEntity.ok(profissionalOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Profissional> save(ProfissionalDTO profissionalDTO) {
		boolean existsEmail = profissionalRepository.existsByEmail(profissionalDTO.getEmail());
		boolean existsCpf = profissionalRepository.existsByCpf(profissionalDTO.getCpf());
		boolean existsTelefone = profissionalRepository.existsByTelefone(profissionalDTO.getTelefone());

		if (existsEmail || existsCpf || existsTelefone) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Profissional profissional = modelMapper.map(profissionalDTO, Profissional.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalRepository.save(profissional));
	}
	
	public Profissional inserir(Profissional profissional, String empresa) {
		 Empresa empresas = empresaRepository.findByNome(empresa);
		 profissional = profissionalRepository.save(profissional);
		 profissional.getEmpresas().add(empresas);
		return profissional;
	}

	@Transactional
	public ResponseEntity<Profissional> update(Long id, ProfissionalUpdateDTO profissionalUpdateDTO) {
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

		if (profissionalOptional.isPresent()) {
			Profissional profissional = profissionalOptional.get();

			if (null != profissionalUpdateDTO.getNome())
				profissional.setNome(profissionalUpdateDTO.getNome());
			
			if (null != profissionalUpdateDTO.getPosicao())
				profissional.setPosicao(profissionalUpdateDTO.getPosicao());
			
			if (null != profissionalUpdateDTO.getProjeto())
				profissional.setProjeto(profissionalUpdateDTO.getProjeto());
			
			if (null != profissionalUpdateDTO.getFuncao())
				profissional.setFuncao(profissionalUpdateDTO.getFuncao());

			if (null != profissionalUpdateDTO.getEmail()) {
				if (!profissionalRepository.existsByEmail(profissionalUpdateDTO.getEmail())) {
					profissional.setEmail(profissionalUpdateDTO.getEmail());
				} else if (!profissional.getEmail().equals(profissionalUpdateDTO.getEmail())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			if (null != profissionalUpdateDTO.getDataDeNascimento())
				profissional.setDataDeNascimento(profissionalUpdateDTO.getDataDeNascimento());

			if (null != profissionalUpdateDTO.getCpf()) {
				if (!profissionalRepository.existsByCpf(profissionalUpdateDTO.getCpf())) {
					profissional.setCpf(profissionalUpdateDTO.getCpf());
				} else if (!profissional.getCpf().equals(profissionalUpdateDTO.getCpf())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			if (null != profissionalUpdateDTO.getTelefone()) {
				if (!profissionalRepository.existsByTelefone(profissionalUpdateDTO.getTelefone())) {
					profissional.setTelefone(profissionalUpdateDTO.getTelefone());
				} else if (!profissional.getTelefone().equals(profissionalUpdateDTO.getTelefone())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			return ResponseEntity.ok(profissionalRepository.save(profissional));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!profissionalRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		profissionalRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public ResponseEntity<Void> add(Long idProfissional, Long idEmpresa) {
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(idProfissional);
		Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);

		if (profissionalOptional.isPresent() && empresaOptional.isPresent()) {
			
			List<Empresa> empresas = profissionalOptional.get().getEmpresas();
			List<Profissional> profissionais = empresaOptional.get().getProfissionais();
			
			Optional<Profissional> profissionalOp = profissionais.stream().filter(profissional -> profissional.getId().equals(idEmpresa))
					.findFirst();
			
			Optional<Empresa> empresaOp = empresas.stream().filter(empresa -> empresa.getId().equals(idProfissional))
					.findFirst();
			
			if (!profissionalOp.isPresent() && !empresaOp.isPresent()) {
				profissionais.add(profissionalOptional.get());
				empresas.add(empresaOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> remove(Long idProfissional, Long idEmpresa) {
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(idProfissional);
		Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);

		if (profissionalOptional.isPresent() && empresaOptional.isPresent()) {
			
			List<Empresa> empresas = profissionalOptional.get().getEmpresas();
			List<Profissional> profissionais = empresaOptional.get().getProfissionais();
			
			Optional<Profissional> profissionalOp = profissionais.stream().filter(profissional -> profissional.getId().equals(idEmpresa))
					.findFirst();
			
			Optional<Empresa> empresaOp = empresas.stream().filter(empresa -> empresa.getId().equals(idProfissional))
					.findFirst();
				
			if (profissionalOp.isPresent() && empresaOp.isPresent()) {
				profissionais.remove(profissionalOptional.get());
				empresas.remove(empresaOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> addTec(Long idTecnologia, Long idProfissional) {
		Optional<Tecnologia> tecnologiaOptional = tecnologiasRepository.findById(idTecnologia);
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(idProfissional);

		if (profissionalOptional.isPresent() && tecnologiaOptional.isPresent()) {
			
			List<Profissional> profissionais = tecnologiaOptional.get().getProfissionais();
			List<Tecnologia> tecnologias = profissionalOptional.get().getTecnologias();
			
			Optional<Tecnologia> tecnologiasOp = tecnologias.stream().filter(tecnologia -> tecnologia.getId().equals(idProfissional))
					.findFirst();

			Optional<Profissional> profissionalOp = profissionais.stream().filter(profissional -> profissional.getId().equals(idTecnologia))
					.findFirst();	
			
			if (!profissionalOp.isPresent() && !tecnologiasOp.isPresent()) {
				tecnologias.add(tecnologiaOptional.get());
				profissionais.add(profissionalOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<Void> removeTec(Long idTecnologia, Long idProfissional) {
		Optional<Tecnologia> tecnologiaOptional = tecnologiasRepository.findById(idTecnologia);
		Optional<Profissional> profissionalOptional = profissionalRepository.findById(idProfissional);

		if (profissionalOptional.isPresent() && tecnologiaOptional.isPresent()) {
			
			List<Profissional> profissionais = tecnologiaOptional.get().getProfissionais();
			List<Tecnologia> tecnologias = profissionalOptional.get().getTecnologias();
			
			Optional<Tecnologia> tecnologiasOp = tecnologias.stream().filter(tecnologia -> tecnologia.getId().equals(idProfissional))
					.findFirst();

			Optional<Profissional> profissionalOp = profissionais.stream().filter(profissional -> profissional.getId().equals(idTecnologia))
					.findFirst();	
			
			if (profissionalOp.isPresent() && tecnologiasOp.isPresent()) {
				tecnologias.remove(tecnologiaOptional.get());
				profissionais.remove(profissionalOptional.get());

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.notFound().build();
	}

}
