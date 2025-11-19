package com.adsimepac.fisioterapia.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private LocalTime horarioAtendimento;
    private String telefone;
    private String endereco;
    private String genero; // M, F, Outro
    private String observacoesGerais;
    @Column(columnDefinition = "TEXT")
    private String sobreOPaciente;
    private Boolean possuiDiabetes = false;
    private Boolean menorDe18Anos = false;
    private Boolean emDestaque = false;

    // Construtor padrão
    public Paciente() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalTime getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(LocalTime horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }

    public String getSobreOPaciente() {
        return sobreOPaciente;
    }

    public void setSobreOPaciente(String sobreOPaciente) {
        this.sobreOPaciente = sobreOPaciente;
    }

    public Boolean getPossuiDiabetes() {
        return possuiDiabetes;
    }

    public void setPossuiDiabetes(Boolean possuiDiabetes) {
        this.possuiDiabetes = possuiDiabetes;
    }

    public Boolean getMenorDe18Anos() {
        return menorDe18Anos;
    }

    public void setMenorDe18Anos(Boolean menorDe18Anos) {
        this.menorDe18Anos = menorDe18Anos;
    }

    public Boolean getEmDestaque() {
        return emDestaque;
    }

    public void setEmDestaque(Boolean emDestaque) {
        this.emDestaque = emDestaque;
    }
}
