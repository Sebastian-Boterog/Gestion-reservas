package com.GestionViajes.gestion_viajes.persistence.mapper;

import com.GestionViajes.gestion_viajes.domain.dto.PagoDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Pago;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "metodoPago", source = "metodoPago")
    @Mapping(target = "fechaPago", source = "fechaPago")
    @Mapping(target = "reservaId", source = "reserva.id")
    PagoDTO toDto(Pago pago);

    @InheritInverseConfiguration
    @Mapping(target = "reserva", ignore = true)
    Pago toEntity(PagoDTO pagoDTO);
}
