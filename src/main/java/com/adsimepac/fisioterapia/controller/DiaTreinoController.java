package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.model.DiaTreino;
import com.adsimepac.fisioterapia.service.DiaTreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dias-treino")
@CrossOrigin(origins = "*")
public class DiaTreinoController {

    @Autowired
    private DiaTreinoService diaTreinoService;

    @GetMapping
    public List<DiaTreino> listar() {
        return diaTreinoService.listarDiasTreino();
    }

    @GetMapping("/{id}")
    public DiaTreino buscarPorId(@PathVariable Long id) {
        return diaTreinoService.buscarPorId(id);
    }

    @PostMapping
    public DiaTreino criar(@RequestBody DiaTreino diaTreino) {
        return diaTreinoService.criarDiaTreino(diaTreino);
    }

    @PutMapping("/{id}")
    public DiaTreino atualizar(@PathVariable Long id, @RequestBody DiaTreino diaTreino) {
        return diaTreinoService.atualizarDiaTreino(id, diaTreino);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        diaTreinoService.excluirDiaTreino(id);
    }
}
