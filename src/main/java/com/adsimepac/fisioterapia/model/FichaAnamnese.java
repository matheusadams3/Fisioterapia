package com.adsimepac.fisioterapia.model;

import javax.persistence.*;

@Entity
@Table(name = "ficha_anamnese")
public class FichaAnamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;

    private String condicoesFisicas;
    private String restricoes;
    private String historicoMedico;
    private String observacoes;

    //  RELACIONAMENTO 1–1 COM PACIENTE
    @OneToOne
    @JoinColumn(name = "paciente_id") // cria coluna FK no banco
    private Paciente paciente;

    // GETTERS E SETTERS
    public Long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public String getCondicoesFisicas() {
        return condicoesFisicas;
    }

    public void setCondicoesFisicas(String condicoesFisicas) {
        this.condicoesFisicas = condicoesFisicas;
    }

    public String getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(String restricoes) {
        this.restricoes = restricoes;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
