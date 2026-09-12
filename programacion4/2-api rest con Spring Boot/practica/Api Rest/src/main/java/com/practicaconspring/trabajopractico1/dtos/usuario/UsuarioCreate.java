package com.practicaconspring.trabajopractico1.dtos.usuario;

import com.practicaconspring.trabajopractico1.entities.Usuario;
import com.practicaconspring.trabajopractico1.enums.Rol;

public record UsuarioCreate(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contrasena,
        Rol rol
) {
    public Usuario toEntity(){ // Le quitamos el parámetro
        Usuario usuario = new Usuario();
        usuario.setNombre(this.nombre);
        usuario.setApellido(this.apellido);
        usuario.setMail(this.mail);
        usuario.setCelular(this.celular);
        usuario.setContrasena(this.contrasena);
        usuario.setRol(this.rol);
        return usuario;
    }
}
