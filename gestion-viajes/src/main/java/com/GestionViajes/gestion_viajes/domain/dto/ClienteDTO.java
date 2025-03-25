package com.GestionViajes.gestion_viajes.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
}