package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Avaliacao;
import com.adsimepac.fisioterapia.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // CRIAR
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    // LISTAR TODOS
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    // BUSCAR POR ID
    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));
    }

    // EDITAR
    public Avaliacao atualizarAvaliacao(Long id, Avaliacao novaAvaliacao) {
        Avaliacao avaliacao = buscarPorId(id);

        avaliacao.setDataAvaliacao(novaAvaliacao.getDataAvaliacao());
        avaliacao.setTipo(novaAvaliacao.getTipo());
        avaliacao.setDescricao(novaAvaliacao.getDescricao());
        avaliacao.setResultado(novaAvaliacao.getResultado());

        return avaliacaoRepository.save(avaliacao);
    }

    // DELETAR
    public void excluirAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
