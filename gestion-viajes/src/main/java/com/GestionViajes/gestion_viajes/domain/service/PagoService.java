package com.GestionViajes.gestion_viajes.domain.service;

import com.GestionViajes.gestion_viajes.domain.dto.PagoDTO;
import com.GestionViajes.gestion_viajes.domain.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public Optional<PagoDTO> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public PagoDTO guardar(PagoDTO pagoDTO) {
        return pagoRepository.save(pagoDTO);
    }

    public PagoDTO actualizar(PagoDTO pagoDTO) {
        return pagoRepository.update(pagoDTO);
    }

    public void eliminar(Long id) {
        pagoRepository.delete(id);
    }

    public boolean existePago(Long id) {
        return pagoRepository.existsById(id);
    }
}
