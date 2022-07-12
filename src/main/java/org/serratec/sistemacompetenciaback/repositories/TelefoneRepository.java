package org.serratec.sistemacompetenciaback.repositories;

import java.util.Optional;

import org.serratec.sistemacompetenciaback.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	Optional<Telefone> findByTelefone(String telefone);
}
