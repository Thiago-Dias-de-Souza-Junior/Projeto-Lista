package com.lista.ProjetoLista01.repository;

import com.lista.ProjetoLista01.model.Nota;
import com.lista.ProjetoLista01.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsuario(Usuario usuario);
}
