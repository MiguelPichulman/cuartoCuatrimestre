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

        // Aquí aplicamos la corrección del profesor: usamos el método de negocio de la entidad
        for (DetallePedidoCreate dto : detallesDto) {
            Producto producto = productoRepository.findById(dto.idProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // La entidad Pedido se encarga de instanciar el detalle y recalcular el total
            pedido.addDetallePedido(dto.cantidad(), producto);
        }

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return PedidoDto.toDto(pedidoGuardado);
    }

    public List<PedidoDto> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoDto::toDto)
                .toList();
    }
}