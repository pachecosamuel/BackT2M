package org.serratec.sistemacompetenciaback.repositories;

import java.util.Optional;

import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
	
	Usuario findByEmail(String email);
	
	Optional<Usuario> findByLoginAndSenha(String login, String senha);
	
	boolean existsByLogin(String login);
	
	boolean existsByEmail(String email);
}
