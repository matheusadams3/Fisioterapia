package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    // ---------------------------------------------------------
    // 🎯 FILTRO COMPLETO: GÊNERO | FAIXA ETÁRIA | STATUS
    // ---------------------------------------------------------
    public List<Paciente> filtrarPacientes(String genero, String faixaEtaria) {

        return pacienteRepository.findAll().stream()
                .filter(p -> filtrarGenero(p, genero))
                .filter(p -> filtrarFaixaEtaria(p, faixaEtaria))
                .collect(Collectors.toList());
    }


    // -------------------------------
    // GÊNERO
    // -------------------------------
    private boolean filtrarGenero(Paciente p, String genero) {
        if (genero == null || genero.isEmpty()) return true;
        return genero.equalsIgnoreCase(p.getGenero());
    }


    // -------------------------------
    // FAIXA ETÁRIA (inclui 17 anos corretamente)
    // -------------------------------
    private boolean filtrarFaixaEtaria(Paciente p, String faixaEtaria) {
        if (faixaEtaria == null || faixaEtaria.isEmpty()) return true;

        int idade = calcularIdade(p.getDataNascimento());

        switch (faixaEtaria.toLowerCase()) {
            case "menores de 18":
                return idade < 18;

            case "18–30":
                return idade >= 18 && idade <= 30;

            case "31–50":
                return idade >= 31 && idade <= 50;

            case "acima de 50":
                return idade > 50;

            default:
                return true;
        }
    }

    // -------------------------------
    // CÁLCULO DE IDADE
    // -------------------------------
    private int calcularIdade(LocalDate nascimento) {
        if (nascimento == null) return 0;
        return Period.between(nascimento, LocalDate.now()).getYears();
    }
}
