package com.practicaconspring.trabajopractico1.dtos.detallePedido;

import com.practicaconspring.trabajopractico1.dtos.producto.ProductoDto;
import com.practicaconspring.trabajopractico1.entities.DetallePedido;

public record DetallePedidoDto(
        Long id,
        int cantidad,
        Double subtotal,
        ProductoDto producto
) {
    public static DetallePedidoDto toDto(DetallePedido detalle) {
        return new DetallePedidoDto(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                // Usamos el if ternario igual que hizo tu cátedra
                detalle.getProducto() != null ? ProductoDto.toDto(detalle.getProducto()) : null
        );
    }
}
