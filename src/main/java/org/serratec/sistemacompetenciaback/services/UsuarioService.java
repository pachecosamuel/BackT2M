package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.UsuarioDTO;
import org.serratec.sistemacompetenciaback.dto.UsuarioUpdateDTO;
import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.serratec.sistemacompetenciaback.enums.TipoUsuario;
import org.serratec.sistemacompetenciaback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailService emailservice;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public ResponseEntity<Usuario> findById(Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Usuario> findByLogin(String login) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);

		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional // (rollbackFor = DataIntegrityViolationException.class)
	public ResponseEntity<Usuario> save(UsuarioDTO usuarioDTO) {
		boolean existsLogin = usuarioRepository.existsByLogin(usuarioDTO.getLogin());
		boolean existsEmail = usuarioRepository.existsByEmail(usuarioDTO.getEmail());

		if (existsLogin || existsEmail) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

		emailservice.sendDados(usuarioDTO);
		
		if (usuarioDTO.getCodigo() == 0) {
			usuario.addPerfil(TipoUsuario.USUARIO);
		} else if (usuarioDTO.getCodigo() == 1) {
			usuario.addPerfil(TipoUsuario.ADMIN);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}

	@Transactional
	public ResponseEntity<Usuario> update(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();

			if (null != usuarioUpdateDTO.getLogin()) {
				if (!usuarioRepository.existsByLogin(usuarioUpdateDTO.getLogin())) {
					usuario.setLogin(usuarioUpdateDTO.getLogin());
				} else if (!usuario.getLogin().equals(usuarioUpdateDTO.getLogin())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			if (null != usuarioUpdateDTO.getNome())
				usuario.setNome(usuarioUpdateDTO.getNome());

			if (null != usuarioUpdateDTO.getEmail()) {
				if (!usuarioRepository.existsByEmail(usuarioUpdateDTO.getEmail())) {
					usuario.setEmail(usuarioUpdateDTO.getEmail());
				} else if (!usuario.getEmail().equals(usuarioUpdateDTO.getEmail())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			if (null != usuarioUpdateDTO.getCodigo()) {
				if (usuarioUpdateDTO.getCodigo() == 0) {
					usuario.updatePerfil(TipoUsuario.USUARIO);
				} else if (usuarioUpdateDTO.getCodigo() == 1) {
					usuario.updatePerfil(TipoUsuario.ADMIN);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}

			if (null != usuarioUpdateDTO.getPermissao())
				usuario.setPermissao(usuarioUpdateDTO.getPermissao());

			if (null != usuarioUpdateDTO.getSenha())
				usuario.setSenha(bCryptPasswordEncoder.encode(usuarioUpdateDTO.getSenha()));

			return ResponseEntity.ok(usuarioRepository.save(usuario));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> deleteById(Long id) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
