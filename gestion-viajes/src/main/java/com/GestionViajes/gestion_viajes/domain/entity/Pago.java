package com.GestionViajes.gestion_viajes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;
}
