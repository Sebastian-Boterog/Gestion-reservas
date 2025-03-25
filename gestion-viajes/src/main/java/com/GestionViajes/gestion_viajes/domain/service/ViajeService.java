package com.GestionViajes.gestion_viajes.domain.service;

import com.GestionViajes.gestion_viajes.domain.dto.ViajeDTO;
import com.GestionViajes.gestion_viajes.domain.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    public List<ViajeDTO> obtenerTodos() {
        return viajeRepository.findAll();
    }

    public Optional<ViajeDTO> obtenerPorId(Long id) {
        return viajeRepository.findById(id);
    }

    public ViajeDTO guardar(ViajeDTO viajeDTO) {
        return viajeRepository.save(viajeDTO);
    }

    public ViajeDTO actualizar(ViajeDTO viajeDTO) {
        return viajeRepository.update(viajeDTO);
    }

    public void eliminar(Long id) {
        viajeRepository.delete(id);
    }

    public long contarViajes() {
        return viajeRepository.count();
    }

    public boolean existeViaje(Long id) {
        return viajeRepository.existsById(id);
    }
}
