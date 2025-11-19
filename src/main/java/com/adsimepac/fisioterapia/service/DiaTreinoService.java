package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.DiaTreino;
import com.adsimepac.fisioterapia.repository.DiaTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaTreinoService {

    @Autowired
    private DiaTreinoRepository diaTreinoRepository;

    // CRIAR
    public DiaTreino criarDiaTreino(DiaTreino diaTreino) {
        return diaTreinoRepository.save(diaTreino);
    }

    // LISTAR
    public List<DiaTreino> listarDiasTreino() {
        return diaTreinoRepository.findAll();
    }

    // BUSCAR POR ID
    public DiaTreino buscarPorId(Long id) {
        return diaTreinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dia de treino não encontrado."));
    }

    // ATUALIZAR
    public DiaTreino atualizarDiaTreino(Long id, DiaTreino novoDia) {
        DiaTreino diaTreino = buscarPorId(id);

        diaTreino.setDiaSemana(novoDia.getDiaSemana());
        diaTreino.setHorario(novoDia.getHorario());

        return diaTreinoRepository.save(diaTreino);
    }

    // DELETAR
    public void excluirDiaTreino(Long id) {
        diaTreinoRepository.deleteById(id);
    }
}