package com.practicaconspring.trabajopractico1.dtos.categoria;

import com.practicaconspring.trabajopractico1.entities.Categoria;

public record CategoriaDto(
        Long id,
        String nombre,
        String descripcion
) {
    public static CategoriaDto toDto(Categoria categoria) {
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}