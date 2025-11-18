package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.model.FichaAnamnese;
import com.adsimepac.fisioterapia.service.FichaAnamneseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaAnamneseController {

    private final FichaAnamneseService service;

    public FichaAnamneseController(FichaAnamneseService service) {
        this.service = service;
    }

    @GetMapping
    public List<FichaAnamnese> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public FichaAnamnese buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public FichaAnamnese criar(@RequestBody FichaAnamnese ficha) {
        return service.salvar(ficha);
    }

    @PutMapping("/{id}")
    public FichaAnamnese atualizar(@PathVariable Long id, @RequestBody FichaAnamnese ficha) {
        return service.atualizar(id, ficha);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
