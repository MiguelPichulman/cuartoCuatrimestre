package com.practicaconspring.trabajopractico1.controllers;

import com.practicaconspring.trabajopractico1.dtos.detallePedido.DetallePedidoCreate;
import com.practicaconspring.trabajopractico1.dtos.pedido.PedidoDto;
import com.practicaconspring.trabajopractico1.enums.FormaPago;
import com.practicaconspring.trabajopractico1.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    // Estructura auxiliar para recibir los datos del pedido desde Postman
    public record PedidoRequest(
            Long usuarioId,
            FormaPago formaPago,
            List<DetallePedidoCreate> detalles
    ) {}

    @PostMapping
    public ResponseEntity<PedidoDto> crearPedido(@RequestBody PedidoRequest request) {
        PedidoDto nuevoPedido = pedidoService.createPedido(
                request.usuarioId(),
                request.formaPago(),
                request.detalles()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}