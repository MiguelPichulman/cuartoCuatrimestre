package com.practicaconspring.trabajopractico1.dtos.pedido;

import com.practicaconspring.trabajopractico1.entities.Pedido;
import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;

public record PedidoEdit(
        Estado estado,
        FormaPago formaPago
) {
    public void applyTo(Pedido pedido) {
        if (this.estado != null) {
            pedido.setEstado(this.estado);
        }
        if (this.formaPago != null) {
            pedido.setFormaPago(this.formaPago);
        }
    }
}