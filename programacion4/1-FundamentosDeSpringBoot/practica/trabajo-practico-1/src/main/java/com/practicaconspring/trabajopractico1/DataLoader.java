package com.practicaconspring.trabajopractico1;

import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaCreate;
import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaDto;
import com.practicaconspring.trabajopractico1.dtos.detallePedido.DetallePedidoCreate;
import com.practicaconspring.trabajopractico1.dtos.producto.ProductoCreate;
import com.practicaconspring.trabajopractico1.dtos.producto.ProductoDto;
import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioCreate;
import com.practicaconspring.trabajopractico1.dtos.usuario.UsuarioDto;
import com.practicaconspring.trabajopractico1.enums.FormaPago;
import com.practicaconspring.trabajopractico1.enums.Rol;
import com.practicaconspring.trabajopractico1.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    public DataLoader(UsuarioService usuarioService,
                      CategoriaService categoriaService,
                      ProductoService productoService,
                      PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("⏳ Cargando datos iniciales...");


       // a) 2 Usuarios

        UsuarioDto user1 = usuarioService.createUsuario(
                new UsuarioCreate("Juan", "Perez", "juan@mail.com", "123456", "pass1", Rol.ADMIN)
        );
        UsuarioDto user2 = usuarioService.createUsuario(
                new UsuarioCreate("Maria", "Gomez", "maria@mail.com", "987654", "pass2", Rol.USUARIO)
        );


        // b) 3 categorias
        CategoriaDto cat1 = categoriaService.createCategoria(new CategoriaCreate("Tecnología", "Electrónica y computación"));
        CategoriaDto cat2 = categoriaService.createCategoria(new CategoriaCreate("Hogar", "Muebles y decoración"));
        CategoriaDto cat3 = categoriaService.createCategoria(new CategoriaCreate("Indumentaria", "Ropa y accesorios"));

        // c) 10 productos

        ProductoDto p1 = productoService.createProducto(new ProductoCreate("Notebook", 1000.0, "Laptop 16GB RAM", 15, "pc.jpg", true, cat1.id()));
        ProductoDto p2 = productoService.createProducto(new ProductoCreate("Smartphone", 500.0, "Celular 128GB", 30, "cel.jpg", true, cat1.id()));
        ProductoDto p3 = productoService.createProducto(new ProductoCreate("Auriculares", 100.0, "Bluetooth", 50, "aur.jpg", true, cat1.id()));
        ProductoDto p4 = productoService.createProducto(new ProductoCreate("Sillón", 300.0, "3 Cuerpos", 5, "sillon.jpg", true, cat2.id()));
        ProductoDto p5 = productoService.createProducto(new ProductoCreate("Mesa de Comedor", 150.0, "Madera roble", 10, "mesa.jpg", true, cat2.id()));
        ProductoDto p6 = productoService.createProducto(new ProductoCreate("Lámpara", 40.0, "Lámpara de pie LED", 20, "lamp.jpg", true, cat2.id()));
        ProductoDto p7 = productoService.createProducto(new ProductoCreate("Zapatillas", 80.0, "Deportivas talle 42", 25, "zap.jpg", true, cat3.id()));
        ProductoDto p8 = productoService.createProducto(new ProductoCreate("Campera", 120.0, "Campera de cuero", 15, "camp.jpg", true, cat3.id()));
        ProductoDto p9 = productoService.createProducto(new ProductoCreate("Remera", 25.0, "Algodón lisa", 100, "rem.jpg", true, cat3.id()));
        ProductoDto p10 = productoService.createProducto(new ProductoCreate("Pantalón", 45.0, "Jean azul", 60, "jean.jpg", true, cat3.id()));

        // d)3 pedidos


        pedidoService.createPedido(
                user1.id(),
                FormaPago.TARJETA,
                List.of(
                        new DetallePedidoCreate(1, 1000.0, p1.id()), // 1 notebook
                        new DetallePedidoCreate(2, 50.0, p9.id())    // 2 remeras (25 c/u)
                )
        );

        pedidoService.createPedido(
                user2.id(),
                FormaPago.EFECTIVO,
                List.of(
                        new DetallePedidoCreate(1, 300.0, p4.id()), // 1 sillon
                        new DetallePedidoCreate(4, 160.0, p6.id())  // 4 lamparas (40 c/u)
                )
        );

        pedidoService.createPedido(
                user1.id(),
                FormaPago.TRANSFERENCIA,
                List.of(
                        new DetallePedidoCreate(1, 80.0, p7.id()),  // 1 par de zapatillas
                        new DetallePedidoCreate(1, 500.0, p2.id()), // 1 smartphone
                        new DetallePedidoCreate(1, 100.0, p3.id())  // 1 auricular (3 detalles en este pedido)
                )
        );

        System.out.println("¡Base de datos cargada exitosamente!");

    }
}