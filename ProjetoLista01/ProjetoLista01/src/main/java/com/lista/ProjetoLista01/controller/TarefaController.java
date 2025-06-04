package com.lista.ProjetoLista01.controller;

import com.lista.ProjetoLista01.model.Tarefa;
import com.lista.ProjetoLista01.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tarefas", tarefaRepository.findAll());
        return "lista"; // Mostra lista.html
    }

    @GetMapping("/nova")
    public String novaTarefa(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        return "formulario"; // Mostra formulario.html
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        return "redirect:/tarefas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("tarefa", tarefa);
        return "formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
        return "redirect:/tarefas";
    }
}