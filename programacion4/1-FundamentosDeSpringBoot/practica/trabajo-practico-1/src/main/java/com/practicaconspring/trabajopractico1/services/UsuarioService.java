package com.practicaconspring.trabajopractico1.services;

import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioCreate;
import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioDto;
import com.practicaconspring.trabajopractico1.entities.Usuario;
import com.practicaconspring.trabajopractico1.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto createUsuario(UsuarioCreate dto) {
        Usuario usuario = dto.toEntity();
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuarioGuardado);
    }
}
