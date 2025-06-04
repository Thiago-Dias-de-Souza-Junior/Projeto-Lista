package com.lista.ProjetoLista01.controller;

import com.lista.ProjetoLista01.model.Anotacao;
import com.lista.ProjetoLista01.model.Usuario;
import com.lista.ProjetoLista01.repository.AnotacaoRepository;
import com.lista.ProjetoLista01.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ListaController {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String mostrarLista(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Anotacao> anotacoes = anotacaoRepository.findByUsuario(usuario);
        model.addAttribute("anotacoes", anotacoes);
        return "lista";
    }

    @PostMapping("/lista")
    public String adicionarAnotacao(@RequestParam String texto, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Anotacao anotacao = new Anotacao();
        anotacao.setTexto(texto);
        anotacao.setUsuario(usuario);
        anotacaoRepository.save(anotacao);

        return "redirect:/lista";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}