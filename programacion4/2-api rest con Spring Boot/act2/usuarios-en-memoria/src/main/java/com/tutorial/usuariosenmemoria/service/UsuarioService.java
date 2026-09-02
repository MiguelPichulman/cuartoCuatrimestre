package com.tutorial.usuariosenmemoria.service;

import com.tutorial.usuariosenmemoria.model.Usuario;
import com.tutorial.usuariosenmemoria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario crearUsuario(Usuario usuario) {
        //ejemplo de validacion simple
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        return repository.guardar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listar();
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        return repository.buscarPorId(id);
    }
}
