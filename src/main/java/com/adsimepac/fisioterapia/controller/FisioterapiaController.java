package com.adsimepac.fisioterapia.controller;

import com.adsimepac.fisioterapia.dto.PacienteDTO;
import com.adsimepac.fisioterapia.model.Paciente;
import com.adsimepac.fisioterapia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FisioterapiaController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Página inicial da clínica.
     * Redireciona para a lista de pacientes.
     */
    @GetMapping({"/", "/inicio"} )
    public String dashboard(Model model) {
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes")
    public String listarPacientes(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Paciente> pacientes;
        if (search != null && !search.isEmpty()) {
            pacientes = pacienteService.search(search);
        } else {
            pacientes = pacienteService.findAll();
        }
        
        // Converter para DTO com idade calculada
        List<PacienteDTO> pacientesDTO = pacientes.stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
        
        model.addAttribute("pacientes", pacientesDTO);
        model.addAttribute("novoPaciente", new Paciente());
        model.addAttribute("dataAtual", LocalDate.now());
        return "pacientes/lista";
    }

    @PostMapping("/pacientes/adicionar")
    public String adicionarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/{id}")
    public String detalhePaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.findById(id);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            PacienteDTO pacienteDTO = new PacienteDTO(paciente);
            model.addAttribute("paciente", pacienteDTO);
            model.addAttribute("presencas", List.of());
            model.addAttribute("acompanhamentos", List.of());
            return "pacientes/detalhe";
        }
        return "redirect:/pacientes";
    }
}
