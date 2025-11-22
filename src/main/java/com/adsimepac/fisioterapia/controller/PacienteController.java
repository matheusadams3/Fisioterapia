package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAll() {
        return pacienteService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente create(@RequestBody Paciente paciente) {
        paciente.setId(null);
        return pacienteService.save(paciente);
    }

    //  ATUALIZAR PACIENTE
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente dadosAtualizados) {
        return pacienteService.findById(id)
                .map(pacienteExistente -> {
                    // Copia as propriedades do objeto recebido para o objeto existente
                    pacienteExistente.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    pacienteExistente.setDataNascimento(dadosAtualizados.getDataNascimento());
                    pacienteExistente.setTelefone(dadosAtualizados.getTelefone());
                    pacienteExistente.setEndereco(dadosAtualizados.getEndereco());
                    pacienteExistente.setGenero(dadosAtualizados.getGenero());
                    pacienteExistente.setObservacoesGerais(dadosAtualizados.getObservacoesGerais());
                    pacienteExistente.setSobrePaciente(dadosAtualizados.getSobrePaciente());
                    pacienteExistente.setPossuiDiabetes(dadosAtualizados.isPossuiDiabetes());
                    pacienteExistente.setMenorDe18Anos(dadosAtualizados.isMenorDe18Anos());
                    pacienteExistente.setEmDestaque(dadosAtualizados.isEmDestaque());

                    Paciente atualizado = pacienteService.save(pacienteExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //  EXCLUIR PACIENTE
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pacienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //  BUSCA POR NOME
    @GetMapping("/search")
    public List<Paciente> search(@RequestParam String termo) {
        return pacienteService.search(termo);
    }

    @GetMapping("/filter")
    public List<Paciente> filtrar(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String faixaEtaria,
            @RequestParam(required = false) String status
    ) {
        return pacienteService.filtrarPacientes(genero, faixaEtaria);
    }


}
