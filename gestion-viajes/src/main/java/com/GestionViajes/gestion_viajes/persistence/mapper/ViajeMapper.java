package com.GestionViajes.gestion_viajes.persistence.mapper;

import com.GestionViajes.gestion_viajes.domain.dto.ViajeDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Viaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ViajeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "destino", source = "destino")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "precio", source = "precio")
    @Mapping(target = "fechaDisponible", source = "fechaDisponible")
    @Mapping(target = "duracion", source = "duracion")
    ViajeDTO toDto(Viaje viaje);

    @InheritInverseConfiguration
    Viaje toEntity(ViajeDTO viajeDTO);
}
