package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Presenca;
import com.adsimepac.fisioterapia.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository presencaRepository;

    public List<Presenca> findByPacienteId(Long pacienteId) {
        return presencaRepository.findByPacienteId(pacienteId);
    }

    public Presenca save(Presenca presenca) {
        return presencaRepository.save(presenca);
    }

    public Optional<Presenca> findById(Long id) {
        return presencaRepository.findById(id);
    }
}
