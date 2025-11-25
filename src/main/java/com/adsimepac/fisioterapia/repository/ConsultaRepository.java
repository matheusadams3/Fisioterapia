package com.adsimepac.fisioterapia.repository;

import com.adsimepac.fisioterapia.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByInicioBetween(LocalDateTime inicio, LocalDateTime fim);

}