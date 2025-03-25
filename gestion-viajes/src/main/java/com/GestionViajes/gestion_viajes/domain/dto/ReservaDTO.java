package com.GestionViajes.gestion_viajes.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDTO {
    private Long id;
    private LocalDate fechaReserva;
    private String estado;
    private Long clienteId;
    private Long viajeId;
}
