package com.adsimepac.fisioterapia.dto;

public class ConsultaDTO {

    private Long id;
    private String inicio;
    private String fim;
    private Long pacienteId;
    private String pacienteNome;
    private String observacoes;

    public ConsultaDTO() {}

    public ConsultaDTO(Long id, String inicio, String fim, Long pacienteId, String pacienteNome, String observacoes) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.pacienteId = pacienteId;
        this.pacienteNome = pacienteNome;
        this.observacoes = observacoes;
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }

    public String getInicio() { return inicio; }
    public void setInicio(String inicio) { this.inicio = inicio; }

    public String getFim() { return fim; }
    public void setFim(String fim) { this.fim = fim; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public String getPacienteNome() { return pacienteNome; }
    public void setPacienteNome(String pacienteNome) { this.pacienteNome = pacienteNome; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}