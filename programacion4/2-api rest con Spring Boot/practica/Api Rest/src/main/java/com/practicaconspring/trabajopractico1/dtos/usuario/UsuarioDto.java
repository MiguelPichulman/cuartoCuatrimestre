package com.practicaconspring.trabajopractico1.dtos.usuario;

import com.practicaconspring.trabajopractico1.entities.Usuario;
import com.practicaconspring.trabajopractico1.enums.Rol;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contrasena,
        Rol rol
) {
    public static  UsuarioDto toDto(Usuario usuario){
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getMail(),
                usuario.getCelular(),
                usuario.getContrasena(),
                usuario.getRol()
        );
    }
}
