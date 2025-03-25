package com.GestionViajes.gestion_viajes.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PagoDTO {
    private Long id;
    private Double monto;
    private String metodoPago;
    private LocalDate fechaPago;
    private Long reservaId;
}
