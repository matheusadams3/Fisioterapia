package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.FichaAnamnese;
import com.adsimepac.fisioterapia.repository.FichaAnamneseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaAnamneseService {

    private final FichaAnamneseRepository repository;

    public FichaAnamneseService(FichaAnamneseRepository repository) {
        this.repository = repository;
    }

    public List<FichaAnamnese> listarTodas() {
        return repository.findAll();
    }

    public FichaAnamnese buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha não encontrada."));
    }

    public FichaAnamnese salvar(FichaAnamnese ficha) {
        return repository.save(ficha);
    }

    public FichaAnamnese atualizar(Long id, FichaAnamnese dadosAtualizados) {
        FichaAnamnese ficha = buscarPorId(id);

        ficha.setCondicoesFisicas(dadosAtualizados.getCondicoesFisicas());
        ficha.setRestricoes(dadosAtualizados.getRestricoes());
        ficha.setHistoricoMedico(dadosAtualizados.getHistoricoMedico());
        ficha.setObservacoes(dadosAtualizados.getObservacoes());

        return repository.save(ficha);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
