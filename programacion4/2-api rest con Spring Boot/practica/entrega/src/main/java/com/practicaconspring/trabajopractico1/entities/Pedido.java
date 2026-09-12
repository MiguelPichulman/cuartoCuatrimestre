package com.practicaconspring.trabajopractico1.entities;

import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;

import com.practicaconspring.trabajopractico1.interfaces.Calculable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Pedido extends Base implements Calculable {

    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    @Builder.Default
    private Double total = 0.0;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetallePedido> detallePedido = new ArrayList<>();

    public void addDetallePedido(int cantidad, Producto producto) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return;
        }

        // Usamos instanciación clásica y setters para evitar conflictos del Builder
        DetallePedido nuevoDetalle = new DetallePedido();
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setSubtotal(cantidad * producto.getPrecio());
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setPedido(this); // Relación bidireccional

        this.detallePedido.add(nuevoDetalle);
        this.calcularTotal();
    }

    public void calcularTotal() {
        // Usamos la variable correcta
        this.total = this.detallePedido.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        Long productoId = producto.getId();
        return this.detallePedido.stream()
                .filter(detalle ->
                        detalle.getProducto() != null &&
                                detalle.getProducto().getId().equals(productoId)
                )
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalle = findDetallePedidoByProducto(producto);
        if (detalle != null) {
            this.detallePedido.remove(detalle);
            this.calcularTotal();
        }
    }
}