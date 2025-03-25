package com.GestionViajes.gestion_viajes.persistence.crud;

import com.GestionViajes.gestion_viajes.domain.entity.Pago;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PagoCrudRepository extends CrudRepository<Pago, Long> {
    Optional<Pago> findById(Long id);
}
