package com.GestionViajes.gestion_viajes.domain.repository;


import com.GestionViajes.gestion_viajes.domain.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    List<ClienteDTO> findAll();
    Optional<ClienteDTO> findById(Long id);
    Optional<ClienteDTO> findByCorreo(String correo);
    ClienteDTO save(ClienteDTO clienteDTO);
    ClienteDTO update(ClienteDTO clienteDTO);
    void delete(Long id);
    boolean existsById(Long id);
    long count();
}
