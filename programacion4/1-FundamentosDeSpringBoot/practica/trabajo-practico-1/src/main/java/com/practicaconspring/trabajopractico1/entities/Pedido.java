package com.practicaconspring.trabajopractico1.entities;

import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;

import com.practicaconspring.trabajopractico1.interfaces.Calculable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends Base implements Calculable {

    private LocalDateTime fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedido = new ArrayList<>();

    @Override
    public void calcularTotal() {
        if (this.detallePedido != null) {
            this.total = this.detallePedido.stream()
                    .mapToDouble(DetallePedido::getSubtotal)
                    .sum();
        } else {
            this.total = 0.0;
        }
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalleExistente = findeDetallePedidoByProducto(producto);

        if (detalleExistente != null) {
            detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
            detalleExistente.setSubtotal(detalleExistente.getCantidad() * producto.getPrecio());
        } else {

            DetallePedido nuevoDetalle = new DetallePedido();
            nuevoDetalle.setCantidad(cantidad);
            nuevoDetalle.setSubtotal(cantidad * producto.getPrecio());
            nuevoDetalle.setProducto(producto);
            nuevoDetalle.setPedido(this);
            this.detallePedido.add(nuevoDetalle);
        }

        this.calcularTotal();
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        if (this.detallePedido == null) return null;

        return this.detallePedido.stream()
                .filter(dp -> dp.getProducto().getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalleAEliminar = findeDetallePedidoByProducto(producto);

        if (detalleAEliminar != null) {
            this.detallePedido.remove(detalleAEliminar);
            detalleAEliminar.setPedido(null); // Rompemos la relación bidireccional
            this.calcularTotal(); // Recalculamos el total del pedido
        }
    }
}