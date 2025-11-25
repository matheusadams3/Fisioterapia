package com.adsimepac.fisioterapia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarioController {

    @GetMapping("/calendario")
    public String calendario() {
        return "calendario"; // nome do arquivo calendario.html
    }
}
