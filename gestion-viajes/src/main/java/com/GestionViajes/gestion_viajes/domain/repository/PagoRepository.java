package com.GestionViajes.gestion_viajes.domain.repository;

import com.GestionViajes.gestion_viajes.domain.dto.PagoDTO;

import java.util.List;
import java.util.Optional;

public interface PagoRepository {
    List<PagoDTO> findAll();
    Optional<PagoDTO> findById(Long id);
    PagoDTO save(PagoDTO pagoDTO);
    PagoDTO update(PagoDTO pagoDTO);
    void delete(Long id);
    boolean existsById(Long id);
}
