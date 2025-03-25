package com.GestionViajes.gestion_viajes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "viaje")
public class Viaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
    private String descripcion;
    private Double precio;
    
    @Column(name = "fecha_disponible")
    private LocalDate fechaDisponible;
    
    private Integer duracion; //en días
}
