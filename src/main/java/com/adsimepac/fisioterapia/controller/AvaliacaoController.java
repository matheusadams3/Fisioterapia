package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.model.Avaliacao;
import com.adsimepac.fisioterapia.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoService.listarAvaliacoes();
    }

    @GetMapping("/{id}")
    public Avaliacao buscarPorId(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id);
    }

    @PostMapping
    public Avaliacao criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.criarAvaliacao(avaliacao);
    }

    @PutMapping("/{id}")
    public Avaliacao atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        return avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }

    @DeleteMapping("/{id}")
    public void excluirAvaliacao(@PathVariable Long id) {
        avaliacaoService.excluirAvaliacao(id);
    }
}
