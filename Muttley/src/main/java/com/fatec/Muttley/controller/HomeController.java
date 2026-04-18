package com.fatec.Muttley.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String exibirMenuInicial() {
        return "front/index";
    }
}
