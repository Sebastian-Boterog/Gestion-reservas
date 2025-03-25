package com.GestionViajes.gestion_viajes.domain.repository;

import com.GestionViajes.gestion_viajes.domain.dto.ReservaDTO;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    List<ReservaDTO> findAll();               // Listar todas las reservas
    Optional<ReservaDTO> findById(Long id);   // Buscar una reserva por ID
    ReservaDTO save(ReservaDTO reservaDTO);   // Guardar una nueva reserva
    ReservaDTO update(ReservaDTO reservaDTO); // Actualizar una reserva existente
    void delete(Long id);                      // Eliminar una reserva
    boolean existsById(Long id);               // Verificar si una reserva existe por ID
    long count();                              // Contar la cantidad total de reservas
}
