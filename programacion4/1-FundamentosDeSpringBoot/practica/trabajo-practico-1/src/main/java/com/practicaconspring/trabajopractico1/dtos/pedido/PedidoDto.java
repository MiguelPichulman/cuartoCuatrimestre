package com.practicaconspring.trabajopractico1.dtos.pedido;

import com.practicaconspring.trabajopractico1.dtos.detallePedido.DetallePedidoDto;
import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioDto;
import com.practicaconspring.trabajopractico1.entities.Pedido;
import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        UsuarioDto usuario,
        List<DetallePedidoDto> detallePedido
) {
    public static PedidoDto toDto(Pedido pedido) {
        // Magia de Java: Convertimos la lista de entidades a lista de DTOs
        List<DetallePedidoDto> detallesDto = pedido.getDetallePedido() != null
                ? pedido.getDetallePedido().stream().map(DetallePedidoDto::toDto).toList()
                : new ArrayList<>();

        return new PedidoDto(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                pedido.getUsuario() != null ? UsuarioDto.toDto(pedido.getUsuario()) : null,
                detallesDto
        );
    }
}
