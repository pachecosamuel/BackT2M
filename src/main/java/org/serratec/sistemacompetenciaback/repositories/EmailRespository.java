package org.serratec.sistemacompetenciaback.repositories;

import java.util.Optional;

import org.serratec.sistemacompetenciaback.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRespository extends JpaRepository<Email, Long> {
	
	Optional<Email> findByEmail(String email);
}
