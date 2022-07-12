package org.serratec.sistemacompetenciaback.repositories;

import org.serratec.sistemacompetenciaback.entity.Conhecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConhecimentoRepository extends JpaRepository<Conhecimento, Long> {

}
