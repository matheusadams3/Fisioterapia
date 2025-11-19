package com.adsimepac.fisioterapia.repository;

import com.adsimepac.fisioterapia.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByPacienteId(Long pacienteId);
}
