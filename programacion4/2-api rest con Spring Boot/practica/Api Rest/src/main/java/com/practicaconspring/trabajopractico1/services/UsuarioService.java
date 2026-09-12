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

    // Método para buscar por ID (Requisito 7)
    public UsuarioDto getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Usuario no encontrado con id: " + id));
        // Usamos el método de tu DTO para transformar la entidad
        return UsuarioDto.toDto(usuario);
    }

    // Método para buscar por Mail (Requisito 8)
    public UsuarioDto getUsuarioByMail(String mail) {
        Usuario usuario = usuarioRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Error: Usuario no encontrado con mail: " + mail));
        return UsuarioDto.toDto(usuario);
    }
}