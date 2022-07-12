package org.serratec.sistemacompetenciaback.repositories;

import org.serratec.sistemacompetenciaback.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);

	boolean existsByTelefone(String telefone);

}
