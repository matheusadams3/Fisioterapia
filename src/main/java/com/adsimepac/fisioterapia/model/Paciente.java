package com.adsimepac.fisioterapia.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String telefone;
    private String endereco;
    private String genero;
    private String observacoesGerais;

    private boolean ativo = true;       // <-- OK
    private boolean inativo = false;    // <-- OK (sem @Column TEXT!!)

    private String sobreOPaciente;
    private boolean possuiDiabetes = false;
    private boolean menorDe18Anos = false;
    private boolean emDestaque = false;

    @OneToOne(mappedBy = "paciente")
    private FichaAnamnese fichaAnamnese;

    @ManyToMany(mappedBy = "pacientes")
    private List<DiaTreino> diasTreino;

    @OneToMany(mappedBy = "paciente")
    private List<Avaliacao> avaliacoes;


    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }

    public LocalTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalTime horarioFim) { this.horarioFim = horarioFim; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getObservacoesGerais() { return observacoesGerais; }
    public void setObservacoesGerais(String observacoesGerais) { this.observacoesGerais = observacoesGerais; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public boolean isInativo() { return inativo; }
    public void setInativo(boolean inativo) { this.inativo = inativo; }

    public String getSobreOPaciente() { return sobreOPaciente; }
    public void setSobreOPaciente(String sobreOPaciente) { this.sobreOPaciente = sobreOPaciente; }

    public boolean isPossuiDiabetes() { return possuiDiabetes; }
    public void setPossuiDiabetes(boolean possuiDiabetes) { this.possuiDiabetes = possuiDiabetes; }

    public boolean isMenorDe18Anos() { return menorDe18Anos; }
    public void setMenorDe18Anos(boolean menorDe18Anos) { this.menorDe18Anos = menorDe18Anos; }

    public boolean isEmDestaque() { return emDestaque; }
    public void setEmDestaque(boolean emDestaque) { this.emDestaque = emDestaque; }
}

