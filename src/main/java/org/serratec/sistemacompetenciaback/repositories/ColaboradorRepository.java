package org.serratec.sistemacompetenciaback.repositories;

import org.serratec.sistemacompetenciaback.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

	boolean existsByUsuario(String usuario);
	
	boolean existsByEmail(String email);
}
