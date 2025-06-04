package com.lista.ProjetoLista01.controller;

import com.lista.ProjetoLista01.model.Usuario;
import com.lista.ProjetoLista01.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String exibirFormularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model,
                                 HttpSession session) {

        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado");
            return "login";
        }

        if (!usuario.getPassword().equals(password)) {
            model.addAttribute("erro", "Senha incorreta");
            return "login";
        }


        session.setAttribute("usuarioLogado", usuario);

        return "redirect:/lista";
    }
}