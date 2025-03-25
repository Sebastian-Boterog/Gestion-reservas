package com.GestionViajes.gestion_viajes.persistence.mapper;

import com.GestionViajes.gestion_viajes.domain.dto.ReservaDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Reserva;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fechaReserva", source = "fechaReserva")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "viajeId", source = "viaje.id")
    ReservaDTO toDto(Reserva reserva);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true) // Evita posibles referencias circulares
    @Mapping(target = "viaje", ignore = true)   // Evita posibles referencias circulares
    Reserva toEntity(ReservaDTO reservaDTO);
}
