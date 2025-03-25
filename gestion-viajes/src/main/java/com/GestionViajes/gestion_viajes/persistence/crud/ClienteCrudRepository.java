package com.GestionViajes.gestion_viajes.persistence.crud;


import com.GestionViajes.gestion_viajes.domain.entity.Cliente;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByCorreo(String correo);
}
