package org.serratec.sistemacompetenciaback.repositories;

import org.serratec.sistemacompetenciaback.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
