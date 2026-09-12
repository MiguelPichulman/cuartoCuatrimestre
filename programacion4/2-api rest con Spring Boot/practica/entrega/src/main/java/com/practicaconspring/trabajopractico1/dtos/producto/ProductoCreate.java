package com.practicaconspring.trabajopractico1.dtos.producto;

import com.practicaconspring.trabajopractico1.entities.Categoria;
import com.practicaconspring.trabajopractico1.entities.Producto;

public record ProductoCreate(
        String nombre,
        double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,
        Long idCategoria
) {
    public Producto ToEntity(Categoria categoria){
        return new Producto(
                this.nombre,
                this.precio,
                this.descripcion,
                this.stock,
                this.imagen,
                this.disponible,
                categoria);
    }
}