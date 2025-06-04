package com.lista.ProjetoLista01.controller;

import com.lista.ProjetoLista01.model.Usuario;
import com.lista.ProjetoLista01.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/")
    public String exibirFormularioCadastroRaiz(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro"; // Exibe cadastro.html
    }


    @GetMapping("/cadastro")
    public String exibirPaginaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro"; // Também exibe cadastro.html
    }


    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute Usuario usuario,
                                    @RequestParam String confirmarSenha,
                                    Model model) {


        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            model.addAttribute("erro", "Usuário já existe!");
            return "cadastro";
        }


        if (!usuario.getPassword().equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem!");
            return "cadastro";
        }


        usuarioRepository.save(usuario);


        return "redirect:/login";
    }
}