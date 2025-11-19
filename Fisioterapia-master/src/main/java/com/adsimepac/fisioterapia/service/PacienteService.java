package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente save(Paciente paciente) {
        // Lógica para definir se o paciente deve estar em destaque
        if (paciente.getPossuiDiabetes() != null && paciente.getPossuiDiabetes()) {
            paciente.setEmDestaque(true);
        } else {
            paciente.setEmDestaque(false);
        }
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> search(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return findAll();
        }
        return pacienteRepository.findByNomeCompletoContainingIgnoreCase(termo);
    }
}
