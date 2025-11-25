package com.adsimepac.fisioterapia.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String observacoes;

    public Consulta() {}

    public Consulta(LocalDateTime inicio, LocalDateTime fim, Paciente paciente, String observacoes) {
        this.inicio = inicio;
        this.fim = fim;
        this.paciente = paciente;
        this.observacoes = observacoes;
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFim() { return fim; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}