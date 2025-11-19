package com.adsimepac.fisioterapia.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Acompanhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    private LocalDate data;

    // Pré
    private String paPre;
    private String glicemiaPre;
    private Integer escalaDorPre;
    private String saturacaoO2Pre;
    private Integer freqCardiacaBPMPre;
    @Column(columnDefinition = "TEXT")
    private String observacaoPre;

    // Pós
    private String paPos;
    private String glicemiaPos;
    private Integer escalaDorPos;
    private String saturacaoO2Pos;
    private Integer freqCardiacaBPMPos;
    @Column(columnDefinition = "TEXT")
    private String observacaoPos;

    // Construtor padrão
    public Acompanhamento() {
    }

    // Getters e Setters (omiti para brevidade, mas seriam necessários em um projeto real)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPaPre() {
        return paPre;
    }

    public void setPaPre(String paPre) {
        this.paPre = paPre;
    }

    public String getGlicemiaPre() {
        return glicemiaPre;
    }

    public void setGlicemiaPre(String glicemiaPre) {
        this.glicemiaPre = glicemiaPre;
    }

    public Integer getEscalaDorPre() {
        return escalaDorPre;
    }

    public void setEscalaDorPre(Integer escalaDorPre) {
        this.escalaDorPre = escalaDorPre;
    }

    public String getSaturacaoO2Pre() {
        return saturacaoO2Pre;
    }

    public void setSaturacaoO2Pre(String saturacaoO2Pre) {
        this.saturacaoO2Pre = saturacaoO2Pre;
    }

    public Integer getFreqCardiacaBPMPre() {
        return freqCardiacaBPMPre;
    }

    public void setFreqCardiacaBPMPre(Integer freqCardiacaBPMPre) {
        this.freqCardiacaBPMPre = freqCardiacaBPMPre;
    }

    public String getObservacaoPre() {
        return observacaoPre;
    }

    public void setObservacaoPre(String observacaoPre) {
        this.observacaoPre = observacaoPre;
    }

    public String getPaPos() {
        return paPos;
    }

    public void setPaPos(String paPos) {
        this.paPos = paPos;
    }

    public String getGlicemiaPos() {
        return glicemiaPos;
    }

    public void setGlicemiaPos(String glicemiaPos) {
        this.glicemiaPos = glicemiaPos;
    }

    public Integer getEscalaDorPos() {
        return escalaDorPos;
    }

    public void setEscalaDorPos(Integer escalaDorPos) {
        this.escalaDorPos = escalaDorPos;
    }

    public String getSaturacaoO2Pos() {
        return saturacaoO2Pos;
    }

    public void setSaturacaoO2Pos(String saturacaoO2Pos) {
        this.saturacaoO2Pos = saturacaoO2Pos;
    }

    public Integer getFreqCardiacaBPMPos() {
        return freqCardiacaBPMPos;
    }

    public void setFreqCardiacaBPMPos(Integer freqCardiacaBPMPos) {
        this.freqCardiacaBPMPos = freqCardiacaBPMPos;
    }

    public String getObservacaoPos() {
        return observacaoPos;
    }

    public void setObservacaoPos(String observacaoPos) {
        this.observacaoPos = observacaoPos;
    }
}
