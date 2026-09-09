package com.tutorial.usuariosenmemoriaerr.service;

import com.tutorial.usuariosenmemoriaerr.dto.UsuarioRequestDTO;
import com.tutorial.usuariosenmemoriaerr.dto.UsuarioResponseDTO;
import com.tutorial.usuariosenmemoriaerr.model.Usuario;
import com.tutorial.usuariosenmemoriaerr.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuario) {
        // Ejemplo de validación simple
        if (usuario.nombre() == null || usuario.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Usuario usuarioCreado = Usuario.builder().nombre(usuario.nombre()).email(usuario.email()).password(usuario.password()).build();
        return UsuarioResponseDTO.fromEntity(repository.save(usuarioCreado));
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public UsuarioResponseDTO buscarUsuario(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un usuario con el ID " + id));
        return UsuarioResponseDTO.fromEntity(usuario);
    }
}
