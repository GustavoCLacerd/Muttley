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

    // =============================
    // NOVO CADASTRO
    // =============================
    @GetMapping("/novo")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "front/cadastro_aluno";
    }

    // =============================
    // SALVAR (NOVO + EDICAO)
    // =============================
    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {

        // Padroniza CPF no formato xxx.xxx.xxx-xx antes de persistir.
        if (aluno.getCpf() != null) {
            String cpfFormatado = aluno.getCpf()
                    .replaceAll("\\D", "")
                    .replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            aluno.setCpf(cpfFormatado);
        }

        if (aluno.getId() != null) {
            // Edicao: recarrega o aluno existente e atualiza somente os campos da tela
            Aluno alunoBanco = alunoRepository.findById(aluno.getId()).orElse(null);

            if (alunoBanco != null) {
                alunoBanco.setNome(aluno.getNome());
                alunoBanco.setCpf(aluno.getCpf());
                alunoBanco.setCurso(aluno.getCurso());
                alunoBanco.setEmail(aluno.getEmail());
                alunoBanco.setSaldoHardSkill(aluno.getSaldoHardSkill());
                alunoBanco.setSaldoSoftSkill(aluno.getSaldoSoftSkill());

                alunoRepository.save(alunoBanco);
            }
        } else {
            alunoRepository.save(aluno);
        }

        return "redirect:/";
    }

    // =============================
    // BUSCAR POR CPF
    // =============================
    @GetMapping("/buscar")
    public String buscarPorCpf(@RequestParam String cpf, Model model) {

        String cpfFormatado = cpf
                .replaceAll("\\D", "")
                .replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

        Aluno aluno = alunoRepository.findByCpf(cpfFormatado).orElse(null);

        if (aluno != null) {
            model.addAttribute("aluno", aluno);
        } else {
            model.addAttribute("mensagem", "Aluno nao encontrado");
        }

        return "front/detalhes_aluno";
    }

    // =============================
    // EDITAR
    // =============================
    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {

        Aluno aluno = alunoRepository.findById(id).orElse(null);

        if (aluno == null) {
            return "redirect:/";
        }

        model.addAttribute("aluno", aluno);
        return "front/cadastro_aluno";
    }

    // =============================
    // EXCLUIR
    // =============================
    @PostMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/";
    }
}
