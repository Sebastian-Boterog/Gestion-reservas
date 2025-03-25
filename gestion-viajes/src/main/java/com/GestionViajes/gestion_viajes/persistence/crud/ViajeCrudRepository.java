package com.GestionViajes.gestion_viajes.persistence.crud;


import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionViajes.gestion_viajes.domain.entity.Viaje;

public interface ViajeCrudRepository extends JpaRepository<Viaje, Long> {
}