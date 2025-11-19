package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Acompanhamento;
import com.adsimepac.fisioterapia.repository.AcompanhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcompanhamentoService {

    @Autowired
    private AcompanhamentoRepository acompanhamentoRepository;

    public List<Acompanhamento> findByPacienteId(Long pacienteId) {
        return acompanhamentoRepository.findByPacienteId(pacienteId);
    }

    public Acompanhamento save(Acompanhamento acompanhamento) {
        return acompanhamentoRepository.save(acompanhamento);
    }

    public Optional<Acompanhamento> findById(Long id) {
        return acompanhamentoRepository.findById(id);
    }
}
