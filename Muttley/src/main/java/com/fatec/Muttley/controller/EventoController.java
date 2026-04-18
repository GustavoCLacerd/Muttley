package com.fatec.Muttley.controller;

import com.fatec.Muttley.controller.dto.EventoForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @GetMapping("/novo")
    public String exibirFormularioNovoEvento(Model model) {
        model.addAttribute("evento", new EventoForm());
        return "front/cadastro_evento";
    }

    // TODO: implementar a persistencia escolhendo a subclasse correta de Evento
    // (Palestra, EventoFPA, etc.) conforme o tipoEvento selecionado no formulario.
    @PostMapping("/salvar")
    public String salvarEvento(@ModelAttribute EventoForm evento) {
        return "redirect:/";
    }
}
