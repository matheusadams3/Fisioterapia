package com.adsimepac.fisioterapia.dto;

public class FichaAnamneseDTO {

    private Long idFicha;
    private String condicoesFisicas;
    private String restricoes;
    private String historicoMedico;
    private String observacoes;

    private Long idPaciente; // relação

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

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
