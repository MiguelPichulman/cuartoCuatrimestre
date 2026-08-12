package com.practicaconspring.trabajopractico1.dtos.detallePedido;

import com.practicaconspring.trabajopractico1.entities.DetallePedido;
import com.practicaconspring.trabajopractico1.entities.Producto;

public record DetallePedidoCreate(
        int cantidad,
        Double subtotal,
        Long idProducto
) {
    public DetallePedido toEntity(Producto producto){
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(this.cantidad);
        detalle.setSubtotal(this.subtotal);
        detalle.setProducto(producto);
        return detalle;
    }
}
