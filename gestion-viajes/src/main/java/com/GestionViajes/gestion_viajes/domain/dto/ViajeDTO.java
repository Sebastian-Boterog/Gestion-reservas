package com.GestionViajes.gestion_viajes.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViajeDTO {
    
    private Long id;
    private String destino;
    private String descripcion;
    private Double precio;
    private LocalDate fechaDisponible;
    private Integer duracion;
}
