package com.practicaconspring.trabajopractico1.dtos.usuario;

import com.practicaconspring.trabajopractico1.entities.Usuario;
import com.practicaconspring.trabajopractico1.enums.Rol;

public record UsuarioEdit(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contrasena,
        Rol rol
) {
    public void applyTo(Usuario usuario) {
        if (this.nombre != null) {
            usuario.setNombre(this.nombre);
        }
        if (this.apellido != null) {
            usuario.setApellido(this.apellido);
        }
        if (this.mail != null) {
            usuario.setMail(this.mail);
        }
        if (this.celular != null) {
            usuario.setCelular(this.celular);
        }
        if (this.contrasena != null) {
            usuario.setContrasena(this.contrasena);
        }
        if (this.rol != null) {
            usuario.setRol(this.rol);
        }
    }
}
