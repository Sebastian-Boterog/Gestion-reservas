package com.GestionViajes.gestion_viajes.persistence.mapper;

import com.GestionViajes.gestion_viajes.domain.dto.ClienteDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "correo", source = "correo")
    @Mapping(target = "telefono", source = "telefono")
    ClienteDTO toDto(Cliente cliente);

    @InheritInverseConfiguration
    //@Mapping(target = "reservas", ignore = true) // Ignoramos la relación con Reservas
    Cliente toEntity(ClienteDTO clienteDTO);
}
