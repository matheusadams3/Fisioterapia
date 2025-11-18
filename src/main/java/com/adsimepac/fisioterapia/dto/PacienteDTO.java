package com.adsimepac.fisioterapia.dto;

import com.adsimepac.fisioterapia.model.Paciente;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class PacienteDTO {
    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private LocalTime horarioAtendimento;
    private String telefone;
    private String endereco;
    private String genero;
    private String observacoesGerais;
    private String sobreOPaciente;
    private boolean possuiDiabetes;
    private boolean menorDe18Anos;
    private boolean emDestaque;
    private Integer idade;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nomeCompleto = paciente.getNomeCompleto();
        this.dataNascimento = paciente.getDataNascimento();
        this.horarioAtendimento = paciente.getHorarioAtendimento();
        this.telefone = paciente.getTelefone();
        this.endereco = paciente.getEndereco();
        this.genero = paciente.getGenero();
        this.observacoesGerais = paciente.getObservacoesGerais();
        this.sobreOPaciente = paciente.getSobreOPaciente();
        this.possuiDiabetes = paciente.isPossuiDiabetes();
        this.menorDe18Anos = paciente.isMenorDe18Anos();
        this.emDestaque = paciente.isEmDestaque();
        
        // Calcular idade
        if (paciente.getDataNascimento() != null) {
            this.idade = Period.between(paciente.getDataNascimento(), LocalDate.now()).getYears();
        }
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalTime getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getGenero() {
        return genero;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public String getSobreOPaciente() {
        return sobreOPaciente;
    }

    public boolean getPossuiDiabetes() {
        return possuiDiabetes;
    }

    public boolean getMenorDe18Anos() {
        return menorDe18Anos;
    }

    public boolean getEmDestaque() {
        return emDestaque;
    }

    public Integer getIdade() {
        return idade;
    }
}
