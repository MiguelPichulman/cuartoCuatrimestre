package com.tutorial.usuariosenmemoria.repository;

import com.tutorial.usuariosenmemoria.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();
    private Long contadorId = 1L;

    public Usuario guardar(Usuario usuario) {
        usuario.setId(contadorId++);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Optional <Usuario>buscarPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}