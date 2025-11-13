package com.adsimepac.fisioterapia.repository;

import com.adsimepac.fisioterapia.model.Acompanhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long> {
    List<Acompanhamento> findByPacienteId(Long pacienteId);
}
