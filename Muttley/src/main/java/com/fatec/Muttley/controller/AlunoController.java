package com.fatec.Muttley.controller;

import com.fatec.Muttley.model.Aluno;
import com.fatec.Muttley.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    // Rota para abrir a tela de cadastro (vazia)
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "cadastro_aluno"; // Nome do arquivo HTML que o grupo vai criar
    }

    // Rota para salvar o aluno vindo do formulário
    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/alunos/menu"; // Volta para o menu principal
    }

    // Rota para buscar por CPF (como no botão 'Buscar' do Figma)
    @GetMapping("/buscar")
    public String buscarPorCpf(@RequestParam String cpf, Model model) {
        alunoRepository.findByCpf(cpf).ifPresent(aluno -> model.addAttribute("aluno", aluno));
        return "detalhes_aluno";
    }
}