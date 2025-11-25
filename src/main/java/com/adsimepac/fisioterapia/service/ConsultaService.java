package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.dto.ConsultaDTO;
import com.adsimepac.fisioterapia.model.Consulta;
import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.repository.ConsultaRepository;
import com.adsimepac.fisioterapia.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarEventos(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByInicioBetween(inicio, fim)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultaDTO criar(ConsultaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta consulta = new Consulta(
                LocalDateTime.parse(dto.getInicio()),
                LocalDateTime.parse(dto.getFim()),
                paciente,
                dto.getObservacoes()
        );

        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO atualizar(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        consulta.setInicio(LocalDateTime.parse(dto.getInicio()));
        consulta.setFim(LocalDateTime.parse(dto.getFim()));
        consulta.setPaciente(paciente);
        consulta.setObservacoes(dto.getObservacoes());

        return toDTO(consultaRepository.save(consulta));
    }

    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }


    private ConsultaDTO toDTO(Consulta c) {
        return new ConsultaDTO(
                c.getId(),
                c.getInicio().toString(),
                c.getFim().toString(),
                c.getPaciente() != null ? c.getPaciente().getId() : null,
                c.getPaciente() != null ? c.getPaciente().getNomeCompleto() : "",
                c.getObservacoes()
        );
    }
}