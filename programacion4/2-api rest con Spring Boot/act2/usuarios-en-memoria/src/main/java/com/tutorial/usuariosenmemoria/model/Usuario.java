package com.tutorial.usuariosenmemoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
}
