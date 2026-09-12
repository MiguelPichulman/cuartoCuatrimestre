package com.practicaconspring.trabajopractico1.entities;

import com.practicaconspring.trabajopractico1.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular ;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
