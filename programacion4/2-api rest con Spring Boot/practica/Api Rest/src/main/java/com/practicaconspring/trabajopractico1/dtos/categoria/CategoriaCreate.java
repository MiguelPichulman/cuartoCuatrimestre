package com.practicaconspring.trabajopractico1.dtos.categoria;

import com.practicaconspring.trabajopractico1.entities.Categoria;

public record CategoriaCreate(
        String nombre,
        String descripcion
) {
    public Categoria ToEntity() {
        Categoria categoria = new Categoria();
        categoria.setNombre(this.nombre);
        categoria.setDescripcion(this.descripcion);
        return categoria;
    }
}
