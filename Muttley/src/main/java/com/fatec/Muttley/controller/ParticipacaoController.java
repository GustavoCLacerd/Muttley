package com.fatec.Muttley.controller;

import com.fatec.Muttley.model.Participacao;
import com.fatec.Muttley.service.ParticipacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/participacoes")
public class ParticipacaoController {

    @Autowired
    private ParticipacaoService participacaoService;

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Participacao participacao) {
        participacaoService.registrarParticipacao(participacao);
        return "redirect:/sucesso"; 
    }
}