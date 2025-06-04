package com.lista.ProjetoLista01.repository;

import com.lista.ProjetoLista01.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
