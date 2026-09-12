package com.practicaconspring.trabajopractico1.controllers;

import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioCreate;
import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioDto;
import com.practicaconspring.trabajopractico1.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;

    // POST: Para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioCreate dto) {
        UsuarioDto nuevoUsuario = usuarioService.createUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    // GET: Buscar usuario por ID (Requisito 7)
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    // GET: Buscar usuario por mail (Requisito 8)
    @GetMapping("/mail/{mail}")
    public ResponseEntity<UsuarioDto> buscarPorMail(@PathVariable String mail) {
        UsuarioDto usuario = usuarioService.getUsuarioByMail(mail);
        return ResponseEntity.ok(usuario);
    }
}
