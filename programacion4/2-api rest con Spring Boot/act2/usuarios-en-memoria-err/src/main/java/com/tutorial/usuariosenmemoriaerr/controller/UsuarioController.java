package com.tutorial.usuariosenmemoriaerr.controller;

import com.tutorial.usuariosenmemoriaerr.dto.UsuarioRequestDTO;
import com.tutorial.usuariosenmemoriaerr.dto.UsuarioResponseDTO;
import com.tutorial.usuariosenmemoriaerr.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public UsuarioResponseDTO crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return service.crearUsuario(dto);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscar(@PathVariable Long id) {
        return service.buscarUsuario(id);
    }
}