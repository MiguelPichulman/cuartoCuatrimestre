package com.practicaconspring.trabajopractico1.dtos.producto;

import com.practicaconspring.trabajopractico1.entities.Categoria;
import com.practicaconspring.trabajopractico1.entities.Producto;

public record ProductoEdit(
        String nombre,
        double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,

        Long idCategoria
) {
    public void applyTo (Producto producto, Categoria categoria) {
        if(this.nombre != null) {
            producto.setNombre(this.nombre);
        }
        if(this.precio != 0) {
            producto.setPrecio(this.precio);
        }
        if(this.descripcion != null) {
            producto.setDescripcion(this.descripcion);
        }
        if(this.imagen != null) {
            producto.setImagen(this.imagen);
        }
        if(this.stock != 0) {
            producto.setStock(this.stock);
        }
        if(this.disponible) {
            producto.setDisponible(true);
        }
        if(categoria != null) {
            producto.setCategoria(categoria);
        }
    }
}
