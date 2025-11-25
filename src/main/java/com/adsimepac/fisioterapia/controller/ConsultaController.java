package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.dto.ConsultaDTO;
import com.adsimepac.fisioterapia.service.ConsultaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(consultaService.listarEventos(inicio, fim));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> criar(@RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizar(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        consultaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}