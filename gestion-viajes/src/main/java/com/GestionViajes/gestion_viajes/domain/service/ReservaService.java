package com.GestionViajes.gestion_viajes.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionViajes.gestion_viajes.domain.dto.ReservaDTO;
import com.GestionViajes.gestion_viajes.domain.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaDTO> obtenerTodas() {
        return reservaRepository.findAll();
    }

    public Optional<ReservaDTO> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public ReservaDTO guardar(ReservaDTO reservaDTO) {
        return reservaRepository.save(reservaDTO);
    }

    public ReservaDTO actualizar(ReservaDTO reservaDTO) {
        return reservaRepository.update(reservaDTO);
    }

    public void eliminar(Long id) {
        reservaRepository.delete(id);
    }

    public long contarReservas() {
        return reservaRepository.count();
    }

    public boolean existeReserva(Long id) {
        return reservaRepository.existsById(id);
    }
    
}