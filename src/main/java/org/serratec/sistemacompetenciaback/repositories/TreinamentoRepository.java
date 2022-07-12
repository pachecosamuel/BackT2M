package org.serratec.sistemacompetenciaback.repositories;

import org.serratec.sistemacompetenciaback.entity.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {

}
