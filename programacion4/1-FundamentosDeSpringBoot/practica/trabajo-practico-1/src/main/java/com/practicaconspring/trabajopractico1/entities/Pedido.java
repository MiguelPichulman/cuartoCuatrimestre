package com.practicaconspring.trabajopractico1.entities;

import com.practicaconspring.trabajopractico1.enums.Estado;
import com.practicaconspring.trabajopractico1.enums.FormaPago;
// El import de Usuario del mismo paquete no es estrictamente necesario, pero no hace daño
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity; // ¡Agregamos Entity!
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor; // ¡Agregamos Lombok!
import lombok.Data; // ¡Agregamos Lombok!
import lombok.NoArgsConstructor; // ¡Agregamos Lombok!

import java.time.LocalDate;
import java.util.List;

@Entity // Fundamental para que H2 cree la tabla
@Data // Genera todos los getters y setters automáticamente en memoria
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends Base {

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedido;

}
