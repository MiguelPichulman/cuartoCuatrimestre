package com.practicaconspring.trabajopractico1.services;

import com.practicaconspring.trabajopractico1.dtos.detallePedido.DetallePedidoCreate;
import com.practicaconspring.trabajopractico1.dtos.pedido.PedidoDto;
import com.practicaconspring.trabajopractico1.entities.*;
import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;
import com.practicaconspring.trabajopractico1.repositories.PedidoRepository;
import com.practicaconspring.trabajopractico1.repositories.ProductoRepository;
import com.practicaconspring.trabajopractico1.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public PedidoDto createPedido(Long idUsuario, FormaPago formaPago, List<DetallePedidoCreate> detallesDto) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setFormaPago(formaPago);
        pedido.setUsuario(usuario);
        pedido.setDetallePedido(new ArrayList<>());

        double totalPedido = 0.0;

        for (DetallePedidoCreate dto : detallesDto) {

            Producto producto = productoRepository.findById(dto.idProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetallePedido detalle = dto.toEntity(producto);

            detalle.setPedido(pedido);

            pedido.getDetallePedido().add(detalle);

            totalPedido += detalle.getSubtotal();
        }

        pedido.setTotal(totalPedido);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return PedidoDto.toDto(pedidoGuardado);
    }
}