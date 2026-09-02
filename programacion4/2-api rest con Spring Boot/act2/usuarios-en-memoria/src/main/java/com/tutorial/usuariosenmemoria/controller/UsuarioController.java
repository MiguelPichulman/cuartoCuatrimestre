package com.tutorial.usuariosenmemoria.controller;

import com.tutorial.usuariosenmemoria.model.Usuario;
import com.tutorial.usuariosenmemoria.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crearUsuario(usuario);
    }
    @GetMapping
    public List<Usuario> listar() {
        return service.listarUsuarios();
    }
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscarUsuario(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}