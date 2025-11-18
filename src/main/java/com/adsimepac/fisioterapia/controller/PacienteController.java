package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // LISTAR TODOS
    @GetMapping
    public List<Paciente> getAll() {
        return pacienteService.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  CRIAR PACIENTE
    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(novoPaciente);
    }

    //  ATUALIZAR PACIENTE
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente dadosAtualizados) {
        return pacienteService.findById(id)
                .map(pacienteExistente -> {
                    pacienteExistente.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    pacienteExistente.setDataNascimento(dadosAtualizados.getDataNascimento());
                    pacienteExistente.setHorarioAtendimento(dadosAtualizados.getHorarioAtendimento());
                    pacienteExistente.setTelefone(dadosAtualizados.getTelefone());
                    pacienteExistente.setEndereco(dadosAtualizados.getEndereco());
                    pacienteExistente.setGenero(dadosAtualizados.getGenero());
                    pacienteExistente.setObservacoesGerais(dadosAtualizados.getObservacoesGerais());
                    pacienteExistente.setSobreOPaciente(dadosAtualizados.getSobreOPaciente());
                    pacienteExistente.setPossuiDiabetes(dadosAtualizados.isPossuiDiabetes());
                    pacienteExistente.setMenorDe18Anos(dadosAtualizados.isMenorDe18Anos());
                    pacienteExistente.setEmDestaque(dadosAtualizados.isEmDestaque());

                    Paciente atualizado = pacienteService.save(pacienteExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //  EXCLUIR PACIENTE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pacienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //  BUSCA POR NOME (search?termo=fulano)
    @GetMapping("/search")
    public List<Paciente> search(@RequestParam String termo) {
        return pacienteService.search(termo);
    }
}