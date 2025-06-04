
package com.lista.ProjetoLista01.repository;

import com.lista.ProjetoLista01.model.Anotacao;
import com.lista.ProjetoLista01.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
    List<Anotacao> findByUsuario(Usuario usuario);
}