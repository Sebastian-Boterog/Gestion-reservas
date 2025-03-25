package com.GestionViajes.gestion_viajes.persistence.crud;

import com.GestionViajes.gestion_viajes.domain.entity.Reserva;

import org.springframework.data.repository.CrudRepository;


public interface ReservaCrudRepository extends CrudRepository<Reserva, Long> {
}
