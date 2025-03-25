package com.GestionViajes.gestion_viajes.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;
}
