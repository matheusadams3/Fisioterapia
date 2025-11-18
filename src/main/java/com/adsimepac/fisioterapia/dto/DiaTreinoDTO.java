package com.adsimepac.fisioterapia.dto;

import java.util.List;

public class DiaTreinoDTO {

    private Long idDia;
    private String diaSemana;
    private String horario;

    // IDs dos pacientes no relacionamento ManyToMany
    private List<Long> pacientesIds;

    // GETTERS E SETTERS
    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long idDia) {
        this.idDia = idDia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<Long> getPacientesIds() {
        return pacientesIds;
    }

    public void setPacientesIds(List<Long> pacientesIds) {
        this.pacientesIds = pacientesIds;
    }
}
