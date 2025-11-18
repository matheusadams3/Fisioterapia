package com.adsimepac.fisioterapia.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dias_treino")
public class DiaTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDia;
    private String diaSemana;
    private String horario;

    // RELAÇÃO N ⟷ N COM PACIENTE
    @ManyToMany
    @JoinTable(
            name = "paciente_dia_treino", // tabela intermediária
            joinColumns = @JoinColumn(name = "id_dia"),
            inverseJoinColumns = @JoinColumn(name = "paciente_id")
    )
    private List<Paciente> pacientes;

    public DiaTreino() {}

    public DiaTreino(Long idDia, String diaSemana, String horario) {
        this.idDia = idDia;
        this.diaSemana = diaSemana;
        this.horario = horario;
    }

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
}
