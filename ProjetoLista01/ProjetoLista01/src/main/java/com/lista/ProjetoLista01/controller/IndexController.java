package com.lista.ProjetoLista01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/inicio")  // Evita conflito com "/"
    public String redirecionarParaLogin() {
        return "redirect:/login";
    }
}