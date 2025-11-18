package com.adsimepac.fisioterapia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;

    private LocalDate dataAvaliacao;

    private String tipo;

    private String descricao;

    private String resultado;

    public Avaliacao() {}

    // RELAÇÃO N → 1 COM PACIENTE
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Avaliacao(Long idAvaliacao, LocalDate dataAvaliacao, String tipo, String descricao, String resultado) {
        this.idAvaliacao = idAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        this.tipo = tipo;
        this.descricao = descricao;
        this.resultado = resultado;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
